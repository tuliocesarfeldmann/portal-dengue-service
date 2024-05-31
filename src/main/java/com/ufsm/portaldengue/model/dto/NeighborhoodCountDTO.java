package com.ufsm.portaldengue.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NeighborhoodCountDTO {
    private String neighborhood;
    private Long count;
}
