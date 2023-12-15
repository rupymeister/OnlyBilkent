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

    @Autowired
    private BoardRequestService boardRequestService;

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
        if (payload == null || userId == null || !userService.existsById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userService.existsById(userId)) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(
                userService.editUser(userId, payload.get("name"), payload.get("surname"), payload.get("p1"), payload.get("bio")),
                HttpStatus.OK);
    }

    @PostMapping("/boardRequest/{userId}")
    public ResponseEntity<String> makeBoardRequest(@PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(userService.requestBoardAccount(new BoardRequest(userId)), HttpStatus.OK);
    }

    // Only Admin should be able to
    @GetMapping("/approveBoardRequest/{userId}")
    public ResponseEntity<String> approveBoardRequest(@PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        BoardRequest boardRequest = boardRequestService.findByUserId(userId);
        return new ResponseEntity<String>(userService.approveBoardAccount(boardRequest), HttpStatus.OK);
    }

    // Only Admin should be able to
    @GetMapping("/rejectBoardRequest/{userId}")
    public ResponseEntity<String> rejectBoardRequest(@PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        BoardRequest boardRequest = boardRequestService.findByUserId(userId);
        return new ResponseEntity<String>(userService.rejectBoardAccount(boardRequest), HttpStatus.OK);
    }

    // Only Admin should be able to
    @GetMapping("/getBoardRequests")
    public ResponseEntity<List<BoardRequest>> getBoardRequests() {
        return new ResponseEntity<List<BoardRequest>>(boardRequestService.findAll(), HttpStatus.OK);
    }

    // Only Admin should be able to

    @PutMapping("/banUser/{userId}")
    public ResponseEntity<String> banUser(@PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>(userService.banUser(userId), HttpStatus.OK);
    }

    @PutMapping("/unbanUser/{userId}")
    public ResponseEntity<String> unbanUser(@PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>(userService.unbanUser(userId), HttpStatus.OK);
    }
}