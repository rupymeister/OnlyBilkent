package com.onlybilkent.model.registration;

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

            User user = new User(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(),
                    request.getBio(), 1, request.getImageData(), emailVerificationToken); // role will be handled later

            userRepository.save(user);

            sendEmailVerificiation(user);
        }

    }

    /**
     * public String confirmToken() {
     * return null;
     * }
     **/

    private void sendEmailVerificiation(User user) {

        MailStructure mailStructure = new MailStructure("Verification for your OnlyBilkent Account", "Any message"); // It
                                                                                                                     // takes
                                                                                                                     // subject
                                                                                                                     // and
                                                                                                                     // the
                                                                                                                     // content.
                                                                                                                     // For
                                                                                                                     // content
                                                                                                                     // we
        // need to implement validation link somehow.
        mailService.sendMail(user.getEmail(), mailStructure);

    }

}
