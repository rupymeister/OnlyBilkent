package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> singleUser(ObjectId id) {
        return userRepository.findById(id);
    }

    public boolean existsById(ObjectId userId) {
        return userRepository.existsById(userId);
    }

    public void postCountIncrement(ObjectId userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPostCount(user.getPostCount() + 1);
            userRepository.save(user);
        } else {// if user does not exist
            
        }
    }
    

}
