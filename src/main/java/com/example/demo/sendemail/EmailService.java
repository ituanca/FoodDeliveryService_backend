package com.example.demo.sendemail;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;

import java.util.Properties;

@Slf4j
@NoArgsConstructor
public class EmailService {

    String email = CustomerCredentials.email;
    String password = CustomerCredentials.password;

    public void sendEmail(String textToBeSent) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        Message msg = new MimeMessage(session);
//        try {
            msg.setFrom(new InternetAddress(email, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("A new order was placed");
            msg.setContent(textToBeSent, "text/html");
            msg.setSentDate(new Date());
            Transport.send(msg);
            log.info("Email was successfully sent from " + email + " to " + email +"!");
//        } catch (MessagingException e) {
//            log.warn("Exception when sending email");
//        }
    }

}
