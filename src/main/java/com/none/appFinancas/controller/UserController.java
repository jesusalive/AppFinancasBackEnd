package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.dto.UserFormDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDTO> allUsers(){
        return userService.allUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDTO findOneUser(@PathVariable Long userId){
        User oldUser = userService.findOne(userId);

        return UserAdapter.userAdapter(oldUser);
    }

    @PostMapping("/users")
    public Object createUser(@RequestBody UserFormDTO user){
        return userService.createUser(user.getNome(), user.getUsername(), user.getPassword());
    }
}
