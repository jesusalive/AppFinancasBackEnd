package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.dto.UserFormDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserDTO> allUsers(){
        return userService.allUsers();
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserDTO findOneUser(@PathVariable Long userId) throws IllegalAccessException {
        User oldUser = userService.findOne(userId);

        return UserAdapter.userAdapter(oldUser);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserFormDTO user){
        return userService.createUser(user.getNome(), user.getUsername(), user.getEmail(),user.getPassword());
    }

    @PutMapping("/users/{userId}")
    public User refreshUser(@PathVariable Long userId, @RequestBody UserFormDTO alterations){
        return userService.refreshUser(userId, alterations);
    }

}
