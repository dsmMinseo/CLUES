package com.javaproject.clues.repository;

import com.javaproject.clues.domain.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where user_id = :user_id")
    List<User> checkoverlap(@Param("user_id")String id);

    @Query("select u from User u where user_name = :user_name and user_email = :user_email")
    List<User> findId(@Param("user_name") String name, @Param("user_email") String email);

    @Query("select u from User u where user_id = :user_id and user_email = :user_email")
    List<User> checkid(@Param("user_id") String id, @Param("user_email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.user_password = :user_password where u.user_id = :user_id")
    void changePw(@Param("user_password") String pw, @Param("user_id") String id);

    @Query("select user_nick from User where user_nick = :user_nick")
    List<User> checknick(@Param("user_nick") String nick);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.user_nick = :user_nick where u.user_serial = :user_serial")
    void setnick(@Param("user_nick") String nick, @Param("user_serial") String serial);

    @Query("select u from User u where u.user_id = :user_id")
    List<User> signin(@Param("user_id") String id);
}
