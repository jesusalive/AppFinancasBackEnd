package com.none.appFinancas.service;

import static org.junit.Assert.*;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Test
    public void deveRetornarListaAdaptada(){
        User user1 = new User("Matheus Gomes", "jesus", "2222");
        User user2 = new User("Maria", "opapa", "1230");
        List<User> users = new ArrayList<>(
                Arrays.asList(user1, user2)
        );

        List<UserDTO> adaptedList = UserAdapter.userListAdapter(users);
        assertNotNull(adaptedList);
    }
}