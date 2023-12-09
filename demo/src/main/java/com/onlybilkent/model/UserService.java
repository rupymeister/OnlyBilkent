package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> singleUser(String id) {
        return userRepository.findUserById(id);
    }

    public boolean existsById(ObjectId userId) {
        return userRepository.existsById(userId);
    }
}
