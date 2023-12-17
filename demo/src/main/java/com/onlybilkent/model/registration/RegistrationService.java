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

    // When you provide an email that already exists in the database, it throws a
    // RuntimeException. But we should change it to show error rather than exception
    // maybe??
    public User registerUser(RegistrationRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        else if (!request.getEmail().contains("@ug.bilkent") && !request.getEmail().contains("@alumni.bilkent")) {
            throw new RuntimeException("Invalid email. Please use your Bilkent email.");
        }

        else {

            String emailVerificationToken = UUID.randomUUID().toString();
            String sixDigitEmailVerificationToken = emailVerificationToken.substring(0, 6); // For ease of user

            int role = 0;

            String email = request.getEmail();
            if (email.contains("@ug.")) {
                role = 1;
            } else if (email.contains("@alumni.")) {
                role = 2;
            }

            User user = new User(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(),
                    request.getBio(), role, "6578bfd71fcb0e1176c52d6c", sixDigitEmailVerificationToken);

            userRepository.save(user);

            sendEmailVerificiation(user);
            return user;
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

    // Just sends an email with the new password
    public void sendNewPasswordMail(User user) {

        String newPassword = UUID.randomUUID().toString().substring(0, 6); // For ease of user

        user.setPassword(newPassword);
        userRepository.save(user);

        MailStructure mailStructure = new MailStructure("New Password for your OnlyBilkent Account",
                "Your new password is: " + newPassword);

        mailService.sendMail(user.getEmail(), mailStructure);
    }

}
