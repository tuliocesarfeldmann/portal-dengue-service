package com.ufsm.portaldengue.model.enums;

public enum StatusEnum {
    UNDER_ANALYSIS(1, "EM ANÁLISE"),
    REJECTED(2, "RECUSADO"),
    ACCEPTED(3, "APROVADO"),
    DONE(4, "CORRIGIDO");

    private final Integer code;
    private final String description;

    StatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static StatusEnum fromString(String text) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.description.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
