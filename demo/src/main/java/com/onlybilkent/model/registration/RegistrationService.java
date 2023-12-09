package com.onlybilkent.model.registration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.onlybilkent.model.User;
import com.onlybilkent.model.UserRepository;

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    // Checking whether email is valid should be done later.
    public void registerUser(RegistrationRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        else {

            String emailVerificationToken = UUID.randomUUID().toString();

            User user = new User(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(),
                    request.getBio(), 1, request.getProfilePic(), emailVerificationToken); // role will be handled later

            userRepository.save(user);

            // sendEmailVerificiation(user);
        }

    }

    /**
     * private void sendEmailVerificiation(User user) {
     * // JavaMailSender
     * }
     **/
}
