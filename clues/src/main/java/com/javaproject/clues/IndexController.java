package com.javaproject.clues;

import com.javaproject.clues.domain.user.User;
import com.javaproject.clues.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.util.Base64.Encoder;

import java.util.List;
import java.util.Random;

@RestController
public class IndexController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/mail")
    public String mail(@RequestParam String email) {
        EmailServicelmpl emailService = new EmailServicelmpl();
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
        emailService.sendSimpleMessage(javaMailSender, email, securityNum);
        return securityNum;
    }

    @PostMapping("/signup")
    public String signup(User user) {
        Encoder encoder = java.util.Base64.getEncoder();
        String serial_string = user.getUser_id() +user.getUser_password();
        String serial = encoder.encodeToString(serial_string.getBytes());
        //serial.substring(0,32);
        user.setUser_serial(serial);
        userRepository.save(user);
        return serial;
    }

    @PostMapping("/overlap")
    public boolean overlap(@RequestParam String user_id) {
        List<User> users = userRepository.checkoverlap(user_id);
        if(users.size() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
