package com.none.appFinancas.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    @Test
    public void deveCriarUmaEntrada(){
        User user = new User("Matheus", "dasds", "dsadasda", "dsaasd@dsadsa.com");
        Deposit deposit = new Deposit(user, "Lanche", 7.80, LocalDate.now(), true);

        System.out.println(deposit);

        assertNotNull(deposit);
    }
}