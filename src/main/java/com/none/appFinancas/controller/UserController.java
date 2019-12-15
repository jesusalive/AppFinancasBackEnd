package com.none.appFinancas.controller;

import com.none.appFinancas.dto.UserFormDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody UserFormDTO user){
        return userService.createUser(user.getNome(), user.getUsername(), user.getPassword());
    }
}
