package com.ufsm.portaldengue.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    private String amenity;

    private String road;

    private String suburb;

    @JsonAlias("city_district")
    private String cityDistrict;

    private String town;

    private String municipality;

    @JsonAlias("state_district")
    private String stateDistrict;

    private String state;

    private String region;

    private String postcode;

    private String country;

    @JsonAlias("country_code")
    private String countryCode;
}