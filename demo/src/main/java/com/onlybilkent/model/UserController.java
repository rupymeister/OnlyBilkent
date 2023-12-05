package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.bson.types.ObjectId;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}