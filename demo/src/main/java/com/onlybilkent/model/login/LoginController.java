package com.onlybilkent.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Updated path
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Maybe there should be another mapping like "/login" for

    @PostMapping("/login/asUser") // Changed to PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return loginService.loginAsUser(request);
    }

    @PostMapping("/login/asAdmin") //
    public ResponseEntity<String> loginAsAdmin(@RequestBody LoginRequest request) {
        return loginService.loginAsAdmin(request);
    }

    @PostMapping("/login/asBoard") //
    public ResponseEntity<String> loginAsBoard(@RequestBody LoginRequest request) {
        return loginService.loginAsBoard(request);
    }
}
