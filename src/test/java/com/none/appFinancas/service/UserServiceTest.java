package com.none.appFinancas.service;

import static org.junit.Assert.*;

import com.none.appFinancas.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void deveCriarUmUser(){
       User user = userService.createUser("Matheus", "jesusalive", "210900");

       assertNotNull(user);
    }
}