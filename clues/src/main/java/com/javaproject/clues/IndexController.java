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
        return emailService.sendSimpleMessage(javaMailSender, email);
    }

    @PostMapping("/signup")
    public boolean signup(User user) {
        Encoder encoder = java.util.Base64.getEncoder();
        String serial_string = user.getUser_id() +user.getUser_password();
        String serial = encoder.encodeToString(serial_string.getBytes());
        user.setUser_serial(serial);
        userRepository.save(user);
        return true;
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

    @PostMapping("/findid")
    public String findid(@RequestParam String user_name, @RequestParam String user_email) {
        List<User> users = userRepository.findId(user_name, user_email);
        if(users.size() == 0) {
            return "error";
        } else {
            User user = users.get(0);
            String id = user.getUser_id();
            StringBuilder b_id = new StringBuilder(id);
            Random random = new Random();
            int num = random.nextInt(id.length()-1);
            b_id.setCharAt(num, '*');
            b_id.setCharAt(num+1, '*');
            id = b_id.toString();
            return id;
        }
    }

    @PostMapping("/pwemail")
    public String pwemail(@RequestParam String user_id, @RequestParam String user_email) {
        List<User> users = userRepository.checkid(user_id, user_email);
        if(users.size() == 0) {
            return "error";
        } else {
            EmailServicelmpl emailServicelmpl = new EmailServicelmpl();
            return emailServicelmpl.sendSimpleMessage(javaMailSender, user_email);
        }
    }

    @PostMapping("/setpw")
    public void setpw(@RequestParam String newpw, @RequestParam String user_id) {
        userRepository.changePw(newpw, user_id);
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String user_id, @RequestParam String user_password) {
        List<User> users = userRepository.signin(user_id);
        if(users.size() == 0) {
            return "wrong id";
        } else {
            User user = users.get(0);
            System.out.println(user.getUser_password());
            if(user.getUser_password().equals(user_password)) {
                return user.getUser_serial();
            } else {
                return "wrong pw";
            }
        }
    }

    @PostMapping("/setnick")
    public boolean setnick(@RequestParam String user_nick, @RequestParam String user_serial) {
        List<User> users = userRepository.checknick(user_nick);
        if(users.size()>0) {
            return false;
        } else {
            userRepository.setnick(user_nick, user_serial);
            return true;
        }
    }
}
