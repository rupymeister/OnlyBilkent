package com.onlybilkent.model.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlybilkent.model.User;
import com.onlybilkent.model.UserRepository;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            if (userRepository.findByEmail(request.getEmail()).get().getPassword().equals(request.getPassword())) {
                return ResponseEntity.ok(userRepository.findByEmail(request.getEmail()).get());
            }

            else {
                return ResponseEntity.badRequest().body("Password is incorrect");
            }

        }

        else
            return ResponseEntity.badRequest().body("This email does not exists");

    }

}