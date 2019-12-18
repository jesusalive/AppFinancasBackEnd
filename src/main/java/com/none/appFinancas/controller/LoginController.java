package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.LoginFormDTO;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public UserDTO signIn(@RequestBody LoginFormDTO form){
        try{
            return UserAdapter.userAdapter(loginService.authUser(form.getUsername(), form.getPassword()));
        }catch (RuntimeException e){
            throw new AuthError(e.getMessage());
        }
    }
}
