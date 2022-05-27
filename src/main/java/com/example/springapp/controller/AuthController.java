package com.example.springapp.controller;

import com.example.springapp.entity.User;
import com.example.springapp.exception.InvalidJwtAuthentificationException;
import com.example.springapp.repository.UserRepo;
import com.example.springapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody AuthRequest request)
    {
        try {
            String name = request.getUserName();
            String token = jwtTokenProvider.createToken(
                    name,
                    userRepo.findUserByUserName(name)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found")).getRoles()
            );
            Optional<User> user = userRepo.findUserByUserName(name);
            if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }
            Map<Object, Object> model = new HashMap<>();
            model.put("userName", name);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("/validate")
    public String validate(String token) throws InvalidJwtAuthentificationException {
        if (jwtTokenProvider.validateToken(token)) {
            return "successfully authentication";
        } else {
            throw new BadCredentialsException("Authentication fail");
        }
    }

    @PostMapping("/registration")
    public String registration(@RequestBody AuthRequest request) {
        try {
            if (userRepo.findUserByUserName(request.getUserName()).isPresent()) {
                throw new RuntimeException("Such a user already exists");
            } else {
                userRepo.save(new User(request.getUserName(), passwordEncoder.encode(request.getPassword()),
                        Collections.singletonList("ROLE_USER")));
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username");
        }
        return "successful registration";
    }
}
