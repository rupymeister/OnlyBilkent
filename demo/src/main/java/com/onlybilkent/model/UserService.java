package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.client.result.UpdateResult;

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

    @Autowired
    private BoardRequestService boardRequestService;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> singleUser(String id) {
        return userRepository.findById(id);
    }

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    public User editUser(String userId, String name, String surname, String newPassword, String newBio) {

        // Step 1: Check if the user exists
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            // Handle the case where the user is not found
            return null;
        }

        // Step 2: Update user fields
        User user = optionalUser.get();
        user.setPassword(newPassword);
        user.setBio(newBio);
        user.setName(name);
        user.setSurname(surname);

        // Step 3: Save the updated user
        userRepository.save(user);

        // Step 4: Verify the update
        UpdateResult updateResult = mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().set("password", newPassword).set("bio", newBio)
                        .set("name", name).set("surname", surname))
                .first();

        // Step 5: Handle the case where no documents were modified
        if (updateResult.getModifiedCount() == 0) {
            // Handle the case where no documents were modified
            return null;
        }

        // Step 6: Return the updated user
        return user;
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

                BoardRequest newBoardRequest = new BoardRequest(user.getId(), request.getClubName(),
                        request.getReason());
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
                user.setClubName(request.getClubName());
                userRepository.save(user);
                boardRequestService.deleteById(request.getRequestId());
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

    public String rejectBoardAccount(@RequestBody BoardRequest request) {
        // find the user
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.isBoardRequest() == true) {

                user.setBoardRequest(false);
                userRepository.save(user);
                notificationService
                        .sendNotification(new Notification(user.getId(), "Your board request has been rejected.")); // send
                                                                                                                    // notification
                                                                                                                    // for
                                                                                                                    // the
                                                                                                                    // user
                                                                                                                    // st
                                                                                                                    // his/her
                                                                                                                    // account
                                                                                                                    // is
                                                                                                                    // rejected
                return "Board request rejected.";
            } else {
                throw new RuntimeException("This user has not requested a board account.");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public String banUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {

            if (optionalUser.get().getRole() == 4) {
                throw new RuntimeException("You cannot ban an admin.");
            } else {
                User user = optionalUser.get();
                user.setBanned(true);
                userRepository.save(user);
                return "User banned.";
            }

        } else {
            throw new RuntimeException("User not found");
        }
    }

    public String unbanUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBanned(false);
            userRepository.save(user);
            return "User unbanned.";
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<BoardRequest> getBoardRequests() {
        return boardRequestRepository.findAll();
    }
}
