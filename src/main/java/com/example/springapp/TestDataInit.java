package com.example.springapp;

import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.entity.User;
import com.example.springapp.repository.DepartmentsRepo;
import com.example.springapp.repository.EmployeesRepo;
import com.example.springapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TestDataInit implements CommandLineRunner {
    @Autowired
    UserRepo userRepo;

    @Autowired
    DepartmentsRepo departmentsRepo;

    @Autowired
    PasswordEncoder pwdEncoder;
    @Override
    public void run(String... arg) throws Exception
    {
//        departmentsRepo.save(new DepartmentsEntity(5L, "dep5"));
//
//        userRepo.save(new User("user", pwdEncoder.encode("pwd"), Collections.singletonList("ROLE_USER")));
//        userRepo.save(new User("admin", pwdEncoder.encode("apass"), Collections.singletonList("ROLE_ADMIN")));
//

    }
}
