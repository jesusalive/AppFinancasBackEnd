package com.none.appFinancas.service.mail;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

    void sendEmail(SimpleMailMessage msg);
}
