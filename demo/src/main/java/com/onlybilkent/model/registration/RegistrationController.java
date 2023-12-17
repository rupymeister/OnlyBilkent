package com.onlybilkent.model.registration;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlybilkent.model.User;
import com.onlybilkent.model.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;

    @PostMapping("/userReg")
    public ResponseEntity<User> register(@RequestBody RegistrationRequest request) {
        User user = registrationService.registerUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("token") String verificationCode) {
        return registrationService.confirmUser(verificationCode);
    }

    // Just sends an email with the new password
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> body) {

        Optional<User> userOpt = registrationService.userRepository.findByEmail(body.get("email"));
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            registrationService.sendNewPasswordMail(user);
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }
    }
}