package com.onlybilkent.model.registration;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.onlybilkent.model.User;
import com.onlybilkent.model.UserRepository;
import com.onlybilkent.model.mail.MailService;
import com.onlybilkent.model.mail.MailStructure;

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    // Checking whether email is valid should be done later.
    public void registerUser(RegistrationRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        else {

            String emailVerificationToken = UUID.randomUUID().toString();

            int role = 0;

            String email = request.getEmail();
            if (email.contains("@ug.")) {
                role = 1;
            } else if (email.contains("@alumni.")) {
                role = 2;
            }

            else {
                return;
            }

            User user = new User(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(),
                    request.getBio(), role, request.getImageId(), emailVerificationToken);

            userRepository.save(user);

            sendEmailVerificiation(user);
        }

    }

    public String confirmUser(String verificationCode) {

        Optional<User> optionalUser = userRepository.findByEmailVerificationToken(verificationCode);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (verificationCode.equals(user.getEmailVerificationToken())) {
                user.setEmailVerified(true);
                userRepository.save(user);
                return "Your account has been verified.";
            } else {
                return "Invalid verification code. Please try again.";
            }
        } else {
            return "Invalid verification code. Please try again.";
        }
    }

    private void sendEmailVerificiation(User user) {

        String verificationCode = user.getEmailVerificationToken();

        MailStructure mailStructure = new MailStructure("Verification for your OnlyBilkent Account",
                "Your verification code is: " + verificationCode);

        mailService.sendMail(user.getEmail(), mailStructure);
    }

}
