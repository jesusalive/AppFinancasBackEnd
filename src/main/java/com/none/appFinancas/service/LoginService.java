package com.none.appFinancas.service;

import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AuthError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    public User authUser(String username, String password){
        User user = userService.findUserByUsername(username);

        if(!user.getPassword().equals(password)){
            throw new AuthError("Senha não corresponde com usuario!");
        }

        return user;
    }

}
