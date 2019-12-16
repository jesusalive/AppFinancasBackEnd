package com.none.appFinancas.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeposityTest {

    @Test
    public void deveCriarUmaEntrada(){
        User user = new User("Matheus", "dasds", "dsadasda");
        Deposity deposity = new Deposity(user, "Lanche", 7.80, LocalDate.now(), true);

        System.out.println(deposity);

        assertNotNull(deposity);
    }
}