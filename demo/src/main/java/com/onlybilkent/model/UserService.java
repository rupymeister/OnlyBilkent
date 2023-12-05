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

    public Optional<User> singleUser(ObjectId id) {
        return userRepository.findById(id);
    }
}