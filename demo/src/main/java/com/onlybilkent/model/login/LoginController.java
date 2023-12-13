package com.onlybilkent.model.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlybilkent.model.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @GetMapping
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}
