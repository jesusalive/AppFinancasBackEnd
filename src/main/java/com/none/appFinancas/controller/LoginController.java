package com.none.appFinancas.controller;

import com.none.appFinancas.dto.*;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.security.UserSS;
import com.none.appFinancas.security.VerifyLoggedUser;
import com.none.appFinancas.security.jwt.JWTUtil;
import com.none.appFinancas.service.UserService;
import com.none.appFinancas.service.mail.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${NEWPASS_KEY}")
    private String key;

    @PostMapping("/login")
    public void signIn(@RequestBody LoginFormDTO form){

    }

    @PostMapping("/forgot")
    public CodeForgotPassDTO forgotPass(@RequestBody ForgotFormDTO username){
        User user = userService.findUserByUsername(username.getUsername());
        return smtpMailSender.sendMailPassRecovery(user.getEmail(), user.getName(), user.getId());
    }

    @PostMapping("/new_pass")
    public RequestNewPassDTO newPassToUser(@RequestBody RequestNewPassFormDTO form) throws IllegalAccessException {
        String formPass = form.getPass();

        if (formPass.equals(key)) {
            User user = userService.findUserByUsername(form.getUsername());
            String newPass = SmtpMailSender.createNewCode();

            userService.refreshUser(user.getId(), new UserFormDTO("", "",
                    "", newPass));

            return new RequestNewPassDTO(user.getUsername(), newPass);
        }

        throw new AuthError("Acesso negado!");
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.loggedUser();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
