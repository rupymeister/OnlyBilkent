package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BoardRequestRepository boardRequestRepository;

    @Autowired
    private NotificationService notificationService;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> singleUser(ObjectId id) {
        return userRepository.findById(id);
    }

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    public User editUser(String userId, String newPassword, String pass2, String newBio) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User existingUser = optionalUser.get();

        if (newPassword != null && !newPassword.isEmpty() && pass2 != null && !pass2.isEmpty()
                && newPassword.equals(pass2)) {
            existingUser.setPassword(newPassword);
        }
        if (newBio != null && !newBio.isEmpty()) {
            existingUser.setBio(newBio);
        }

        User updatedUser = userRepository.save(existingUser);

        Update update = new Update();
        if (newPassword != null && !newPassword.isEmpty() && pass2 != null && !pass2.isEmpty()
                && newPassword.equals(pass2)) {
            update.set("id.$.password", newPassword);
        }
        if (newBio != null && !newBio.isEmpty()) {
            update.set("id.$.bio", newBio);
        }

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(update)
                .first();

        return updatedUser;
    }

    // write a method to get user by id here
    public User getUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new RuntimeException("User not found");
    }

    // write a method to save user here
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public String requestBoardAccount(@RequestBody BoardRequest request) {
        // find the user
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isBoardRequest() == false) {

                BoardRequest newBoardRequest = new BoardRequest(user.getId());
                boardRequestRepository.save(newBoardRequest);
                user.setBoardRequest(true);
                userRepository.save(user);
                return "Board request sent.";

            } else if (user.getRole() == 3) {
                throw new RuntimeException("You are already a board member.");
            }

            else {
                throw new RuntimeException("You have already requested a board account. Wait for admin approval.");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public String approveBoardAccount(@RequestBody BoardRequest request) {
        // find the user
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isBoardRequest() == true) {

                user.setRole(3);
                userRepository.save(user);
                notificationService
                        .sendNotification(new Notification(user.getId(), "Your board request has been approved.")); // send
                                                                                                                    // notification
                                                                                                                    // for
                                                                                                                    // the
                                                                                                                    // user
                                                                                                                    // st
                                                                                                                    // his/her
                                                                                                                    // account
                                                                                                                    // is
                                                                                                                    // approved
                return "Board request approved.";
            } else {
                throw new RuntimeException("This user has not requested a board account.");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
