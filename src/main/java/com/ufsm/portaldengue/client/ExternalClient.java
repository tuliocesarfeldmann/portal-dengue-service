package com.ufsm.portaldengue.client;

import com.ufsm.portaldengue.model.dto.ExternalAddressDetailsDTO;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Component;

@Component
public interface ExternalClient {

    @RequestLine("GET /reverse?lat={lat}&lon={lon}&api_key={apiKey}")
    ExternalAddressDetailsDTO getDetailsAddress(@Param("lat") String lat,
                                                @Param("lon") String lon,
                                                @Param("apiKey") String apiKey);

}
