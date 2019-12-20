package com.none.appFinancas.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthError extends RuntimeException {
    public AuthError(String message) {
        super(message);
    }
}
