package com.none.appFinancas.service.mail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SmtpMailSenderTest {

    @Test
    public void deveGerarSenhaAleatoria(){
        String pass = this.createNewPass();
        System.out.println(pass);

        Assert.assertNotNull(pass);
    }

    public String createNewPass() {
        Random generateNumber = new Random();
        List<String> strings = new ArrayList<>(
                Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n",
                        "o","p","q","r","s","u","v","w","x","y","z", "#","$","%","&")
        );

        String newPass = "";

        for (int i = 0; i <= 7; i++){
            newPass += (strings.get(generateNumber.nextInt(strings.size())));
        }

        return newPass;
    }
}
