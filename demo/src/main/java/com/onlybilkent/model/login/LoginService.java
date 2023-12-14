package com.onlybilkent.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onlybilkent.model.UserRepository;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> login(LoginRequest loginRequest) {

        if (userRepository.existsByEmail(loginRequest.getEmail())) {

            if (userRepository.findByEmail(loginRequest.getEmail()).get().getPassword()
                    .equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(userRepository.findByEmail(loginRequest.getEmail()).get());
            } else {
                return ResponseEntity.badRequest().body("Wrong password");
            }
        }

        else {
            return ResponseEntity.badRequest().body("User does not exist");
        }
    }

}
