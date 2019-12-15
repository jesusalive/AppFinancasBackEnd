package com.none.appFinancas.service;

import com.none.appFinancas.entity.User;
import com.none.appFinancas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String nome, String username, String password){
        return userRepository.save(new User(nome, username, password));
    }

}
