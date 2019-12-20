package com.none.appFinancas.controller;

import com.none.appFinancas.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void signIn(@RequestBody LoginFormDTO form){
    }
}
