package com.none.appFinancas.service.mail;

import com.none.appFinancas.dto.CodeForgotPassDTO;
import com.none.appFinancas.dto.UserFormDTO;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SmtpMailSender extends AbstractEmailService {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSender mailSender;

    @Value("${default.sender}")
    private String sender;

    public CodeForgotPassDTO sendMailPassRecovery(String email, String name, Long id){
        String code = createNewCode();
        SimpleMailMessage msg = prepareMailPassRecovery(email, name, code);
        sendEmail(msg);

        return new CodeForgotPassDTO(email, code);
    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        mailSender.send(msg);
    }

    protected SimpleMailMessage prepareMailPassRecovery(String email, String name, String code) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(email);
        sm.setFrom(sender);
        sm.setSubject("Recuperação de senha - JMoney App");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Olá, " + name + "! \n" + "Você solicitou uma recuperação de senha para sua conta no JMoney? \n" +
                "Se sim ai vai o codigo para redefinir sua senha, se nao ignore esse email :D \n" +
                "\nCÓDIGO: " + code);

        return sm;
    }

    public static String createNewCode() {
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
