package com.ufsm.portaldengue.model.dto;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    @NotNull
    private String email;

    @NotNull
    private String password;
}

