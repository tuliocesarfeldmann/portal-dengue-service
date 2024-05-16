package com.ufsm.portaldengue.service;

import com.ufsm.portaldengue.client.ExternalClient;
import com.ufsm.portaldengue.model.dto.*;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.PointDetails;
import com.ufsm.portaldengue.model.entity.PointSituation;
import com.ufsm.portaldengue.model.enums.StatusEnum;
import com.ufsm.portaldengue.repository.PointDetailsRepository;
import com.ufsm.portaldengue.repository.PointRepository;

import java.util.List;

import com.ufsm.portaldengue.repository.PointSituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Service
public class PointService {
    @Autowired
    PointRepository repository;

    @Autowired
    PointSituationRepository pointSituationRepository;

    @Autowired
    ExternalClient externalClient;

    @Autowired
    PointDetailsRepository pointDetailsRepository;

    @Value("${application.client.key.external}")
    private String apiKey;

    public Point register(Point point) {
        return repository.save(point);
    }

    public List<Point> listPointsRequiringAcceptance() {
      return repository.findByPointSituation(PointSituation.getDefaultPointSituation());
    }

    public void updateStatus(Long pointId, StatusEnum status) {
        PointSituation pointSituation = mapStatusToSituation(status);

        repository.updateStatusById(pointId, pointSituation);
    }

    private PointSituation mapStatusToSituation(StatusEnum status) {
        return pointSituationRepository
                .findByDescription(status.getDescription())
                .orElseThrow(() -> new RuntimeException("Situação não encontrada"));
    }

    public AddressDTO getAddressDetails(String lat, String lon) {
        ExternalAddressDetailsDTO address = externalClient.getDetailsAddress(
                lat,
                lon,
                apiKey);

        return AddressDTO.builder()
                .road(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getRoad)
                        .orElse("Interior"))
                .town(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getTown)
                        .orElse("N/A"))
                .neighborhood(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getNeighborhood)
                        .orElseGet(() -> ofNullable(address.getAddress())
                                .map(ExternalAddressDTO::getSuburb)
                                .orElseGet(() -> ofNullable(address.getAddress())
                                        .map(ExternalAddressDTO::getVillage)
                                        .orElse("Interior"))))
                .postcode(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getPostcode)
                        .orElse("N/A"))
                .state(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getState)
                        .orElse("N/A"))
                .country(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getCountry)
                        .orElse("N/A"))
                .countryCode(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getCountryCode)
                        .orElse("N/A"))
                .region(ofNullable(address.getAddress())
                        .map(ExternalAddressDTO::getRegion)
                        .orElse("N/A"))
                .build();
    }

    @Transactional
    public void confirmPoint(ConfirmPointDTO confirmPoint) {
        PointDetails pointDetails = PointDetails.builder()
                .point(confirmPoint.getPoint())
                .road(confirmPoint.getAddress().getRoad())
                .neighborhood(confirmPoint.getAddress().getNeighborhood())
                .town(confirmPoint.getAddress().getTown())
                .state(confirmPoint.getAddress().getState())
                .cep(confirmPoint.getAddress().getPostcode())
                .country(confirmPoint.getAddress().getCountry())
                .build();

        pointDetailsRepository.save(pointDetails);

        updateStatus(confirmPoint.getPoint().getId(), StatusEnum.ACCEPTED);
    }
}
