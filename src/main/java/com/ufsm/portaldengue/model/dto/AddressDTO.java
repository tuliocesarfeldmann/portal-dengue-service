package com.ufsm.portaldengue.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressDTO {

    private String road;

    private String neighborhood;

    private String town;

    private String state;

    private String region;

    private String postcode;

    private String country;

    private String countryCode;
}