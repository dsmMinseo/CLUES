package com.javaproject.clues.repository;

import com.javaproject.clues.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select user_id from User where user_id = :user_id")
    List<User> checkoverlap(@Param("user_id")String id);
}
