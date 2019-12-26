package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.dto.UserFormDTO;
import com.none.appFinancas.dto.UserVerifyDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.security.VerifyLoggedUser;
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
    public UserDTO findOneUser(@PathVariable Long userId) throws IllegalAccessException {
        VerifyLoggedUser.verifyLoggedUser(userId);
        User oldUser = userService.findOne(userId);

        return UserAdapter.userAdapter(oldUser);
    }

    @PostMapping("/verify")
    public UserVerifyDTO verifyEmail(@RequestBody UserFormDTO credentials){
        try{
            if(!credentials.getEmail().trim().isEmpty()){
                return new UserVerifyDTO(userService.userEmailExists(credentials.getEmail()));
            }

            if(!credentials.getUsername().trim().isEmpty()){
                return new UserVerifyDTO(userService.usernameExists(credentials.getUsername()));
            }
        }catch (RuntimeException e){
            throw new AuthError(e.getMessage());
        }

        return null;
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
