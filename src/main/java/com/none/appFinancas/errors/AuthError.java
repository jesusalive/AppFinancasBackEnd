package com.none.appFinancas.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthError extends RuntimeException {
    public AuthError(String message) {
        super(message);
    }
}
