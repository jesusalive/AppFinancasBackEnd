package com.none.appFinancas.service;

import com.none.appFinancas.entity.Outs;
import com.none.appFinancas.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OutsServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private OutsService outsService;

    @Test
    public void deveCriarOut(){
        Object out = outsService.createOut(1L, "Nada", 12.0, "2019-12-15");

        System.out.println(out);

        assertNotNull(out);
    }
}