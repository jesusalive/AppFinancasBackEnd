package com.none.appFinancas.dto;

public class UserVerifyDTO {
    private Boolean exists;

    public UserVerifyDTO(Boolean exists) {
        this.exists = exists;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }
}
