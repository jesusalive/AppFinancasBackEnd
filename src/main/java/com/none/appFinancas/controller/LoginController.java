package com.none.appFinancas.controller;

import com.none.appFinancas.dto.CodeForgotPassDTO;
import com.none.appFinancas.dto.ForgotFormDTO;
import com.none.appFinancas.dto.LoginFormDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.service.UserService;
import com.none.appFinancas.service.mail.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @PostMapping("/login")
    public void signIn(@RequestBody LoginFormDTO form){

    }

    @PostMapping("/forgot")
    public CodeForgotPassDTO forgotPass(@RequestBody ForgotFormDTO username){
        User user = userService.findUserByUsername(username.getUsername());
        return smtpMailSender.sendMailPassRecovery(user.getEmail(), user.getName(), user.getId());
    }
}
