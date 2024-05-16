package com.ufsm.portaldengue.model.dto;

import com.ufsm.portaldengue.model.entity.Point;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmPointDTO {
    private Point point;
    private AddressDTO address;
}
