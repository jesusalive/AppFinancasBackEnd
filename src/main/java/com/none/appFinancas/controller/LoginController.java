package com.none.appFinancas.controller;

import com.none.appFinancas.dto.CodeForgotPassDTO;
import com.none.appFinancas.dto.ForgotFormDTO;
import com.none.appFinancas.dto.LoginFormDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.security.UserSS;
import com.none.appFinancas.security.VerifyLoggedUser;
import com.none.appFinancas.security.jwt.JWTUtil;
import com.none.appFinancas.service.UserService;
import com.none.appFinancas.service.mail.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SmtpMailSender smtpMailSender;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public void signIn(@RequestBody LoginFormDTO form){

    }

    @PostMapping("/forgot")
    public CodeForgotPassDTO forgotPass(@RequestBody ForgotFormDTO username){
        User user = userService.findUserByUsername(username.getUsername());
        return smtpMailSender.sendMailPassRecovery(user.getEmail(), user.getName(), user.getId());
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.loggedUser();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
