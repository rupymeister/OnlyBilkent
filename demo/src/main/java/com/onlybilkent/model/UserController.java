package com.onlybilkent.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable ObjectId userId) {
        return new ResponseEntity<Optional<User>>(userService.singleUser(userId), HttpStatus.OK);
    }

    @PutMapping("/editUser/{userId}")
    public ResponseEntity<User> editUser(@RequestBody Map<String, String> payload, @PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(userService.editUser(userId, payload.get("password"), payload.get("bio")),
                HttpStatus.OK);
    }

    


}