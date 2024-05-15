package com.ufsm.portaldengue.client;

import com.ufsm.portaldengue.model.dto.AddressDetailsDTO;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Component;

@Component
public interface ExternalClient {

    @RequestLine("GET /reverse?lat={lat}&lon={lon}&api_key={apiKey}")
    AddressDetailsDTO getDetailsAddress(@Param("lat") Double lat,
                                        @Param("lon") Double lon,
                                        @Param("apiKey") String apiKey);

}
