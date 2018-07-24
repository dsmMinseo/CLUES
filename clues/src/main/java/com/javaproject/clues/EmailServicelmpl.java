package com.javaproject.clues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServicelmpl {


    public void sendSimpleMessage(JavaMailSender javaMailSender,String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("doyeong1209@gmail.com");
        message.setTo(to);
        message.setSubject("인증번호");
        message.setText(text);
        javaMailSender.send(message);
    }
}
