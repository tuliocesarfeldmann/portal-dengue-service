package com.ufsm.portaldengue.service;

import com.ufsm.portaldengue.client.ExternalClient;
import com.ufsm.portaldengue.model.dto.AddressDetailsDTO;
import com.ufsm.portaldengue.model.dto.UpdateStatusDTO;
import com.ufsm.portaldengue.model.entity.Point;
import com.ufsm.portaldengue.model.entity.PointSituation;
import com.ufsm.portaldengue.model.enums.StatusEnum;
import com.ufsm.portaldengue.repository.PointRepository;

import java.util.List;

import com.ufsm.portaldengue.repository.PointSituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    PointRepository repository;

    @Autowired
    PointSituationRepository pointSituationRepository;

    @Autowired
    ExternalClient externalClient;

    public Point register(Point point) {
        return repository.save(point);
    }

    public List<Point> listPointsRequiringAcceptance() {
      return repository.findByPointSituation(PointSituation.getDefaultPointSituation());
    }

    public void updateStatus(UpdateStatusDTO updateStatus) {
        StatusEnum status = StatusEnum.fromString(updateStatus.getStatus());

        PointSituation pointSituation = mapStatusToSituation(status);

        repository.updateStatusById(updateStatus.getPointId(), pointSituation);
    }

    private PointSituation mapStatusToSituation(StatusEnum status) {
        return pointSituationRepository
                .findByDescription(status.getDescription())
                .orElseThrow(() -> new RuntimeException("Situação não encontrada"));
    }

    public AddressDetailsDTO getAddressDetails(Point point) {
        return externalClient.getDetailsAddress(
                point.getLatitude(),
                point.getLongitude(),
                "");
    }
}
