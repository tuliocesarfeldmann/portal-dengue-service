package com.ufsm.portaldengue.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotNull
    private String cpf;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
