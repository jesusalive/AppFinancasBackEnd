package com.none.appFinancas.security;

import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.security.UserSS;
import com.none.appFinancas.service.UserService;

public class VerifyLoggedUser {
    public static void verifyLoggedUser(Long id){
        UserSS loggedUser = UserService.loggedUser();

        if(loggedUser == null || !id.equals(loggedUser.getId())){
            throw new AuthError("Acesso negado!");
        }

    }
}
