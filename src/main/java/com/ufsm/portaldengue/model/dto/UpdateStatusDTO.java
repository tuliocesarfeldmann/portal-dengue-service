package com.ufsm.portaldengue.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusDTO {
    private Long pointId;
    private String status;
}
