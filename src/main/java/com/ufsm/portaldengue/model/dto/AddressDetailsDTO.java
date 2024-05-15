package com.ufsm.portaldengue.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDetailsDTO {
    @JsonAlias("place_id")
    private String placeId;

    private String licence;

    @JsonAlias("osm_type")
    private String osmType;

    @JsonAlias("osm_id")
    private long osmId;

    private String lat;

    private String lon;

    @JsonAlias("display_name")
    private String displayName;

    private AddressDTO address;

    private String[] boundingbox;
}