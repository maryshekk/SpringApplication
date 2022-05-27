package com.example.springapp.repository;

import com.example.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Long> {
    @Query("select u from User u where u.userName =:username")
    Optional<User> findUserByUserName(@Param("username") String userName);

}