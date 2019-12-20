package com.none.appFinancas.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileUserTest {

    @Test
    public void deveRetornarRoleAdmin(){
        ProfileUser profile = ProfileUser.USER;
        String role = profile.getRole();

        System.out.println(role);

        assertNotNull(role);
    }

}