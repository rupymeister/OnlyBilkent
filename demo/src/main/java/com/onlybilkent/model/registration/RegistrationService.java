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

            String emailVerificationTokenUpdated = emailVerificationToken.substring(0, 6);

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
                    request.getBio(), role, "6578bfd71fcb0e1176c52d6c", emailVerificationTokenUpdated);

            userRepository.save(user);

            sendEmailVerificiation(user);
        }

    }

    public String confirmUser(String verificationCode, String userId) {

        User user = userRepository.findById(userId);

        System.out.println("VerificationCode: " + verificationCode + " user.getEmailVerificationToken: "
                + user.getEmailVerificationToken());

        System.out.println(verificationCode.equals(user.getEmailVerificationToken()));

        if (verificationCode.equals(user.getEmailVerificationToken())) {
            user.setEmailVerified(true);
            userRepository.save(user);
            return "Your account has been verified.";
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
