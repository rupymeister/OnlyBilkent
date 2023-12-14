package com.onlybilkent.model.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.onlybilkent.model.User;
import com.onlybilkent.model.UserRepository;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                return new ResponseEntity(user.getId(), HttpStatus.OK); // Returns ResponseEntity<Object> with User
            } else {
                return ResponseEntity.badRequest().body("Password is incorrect"); // Returns ResponseEntity<Object> with String
            }
        } else {
            return ResponseEntity.badRequest().body("This email does not exist"); // Returns ResponseEntity<Object> with String
        }
    }

}