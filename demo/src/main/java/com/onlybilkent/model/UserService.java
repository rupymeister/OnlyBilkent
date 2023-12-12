package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> singleUser(ObjectId id) {
        return userRepository.findById(id);
    }

    public boolean existsById(String userId) {
        return userRepository.existsById(userId);
    }

    public User editUser(String userId, String newPassword, String newBio) {
        User existingUser = userRepository.findById(userId);

        if (newPassword != null && !newPassword.isEmpty()) {
            existingUser.setPassword(newPassword);
        }
        if (newBio != null && !newBio.isEmpty()) {
            existingUser.setBio(newBio);
        }

        User updatedUser = userRepository.save(existingUser);

        Update update = new Update();
        if (newPassword != null && !newPassword.isEmpty()) {
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
}
