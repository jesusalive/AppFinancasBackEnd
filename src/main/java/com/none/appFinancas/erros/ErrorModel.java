package com.none.appFinancas.erros;

public class ErrorModel {
    private String error;

    public String getError() {
        return error;
    }

    public ErrorModel(String message) {
        this.error = message;
    }
}
