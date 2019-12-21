package com.none.appFinancas.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void deveCriarUsuario(){
        User user = new User("Matheus", "jesusalive", "210900", "dsasda@dasdsa.com");

        System.out.println(user);
        assertNotNull(user);
    }
}