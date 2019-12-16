package com.none.appFinancas.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AtributeNullException extends RuntimeException {
    public AtributeNullException(String message) {
        super(message);

    }
}
