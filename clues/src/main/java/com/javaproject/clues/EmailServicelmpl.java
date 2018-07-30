package com.javaproject.clues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailServicelmpl {


    public String sendSimpleMessage(JavaMailSender javaMailSender,String to) {
        StringBuffer temp_secutiryNum = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            int num = random.nextInt(2);
            switch (num) {
                case 0:
                    temp_secutiryNum.append((char) (random.nextInt(26) + 65 ));
                    break;
                case 1:
                    temp_secutiryNum.append(random.nextInt(10));
                    break;
            }
        }
        String securityNum = temp_secutiryNum.toString();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("doyeong1209@gmail.com");
        message.setTo(to);
        message.setSubject("인증번호");
        message.setText(securityNum);
        javaMailSender.send(message);
        return securityNum;
    }
}
