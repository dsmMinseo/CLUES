package com.javaproject.clues.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    private String user_serial;

    private String user_password;

    private String user_id;

    private String user_name;

    private String user_email;

    private String user_nick;

    private int user_money = 0;

    private int user_win = 0;

    private int user_lose = 0;
}
