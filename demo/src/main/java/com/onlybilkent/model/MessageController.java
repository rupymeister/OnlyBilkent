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
@RequestMapping("/messages")
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessage() {
        List<Message> messages = messageService.allMessages();
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @PostMapping("/sendMessage/{userId}/{receiverId}")
    public ResponseEntity<Message> sendMessage(@RequestBody Map<String, String> payload, @PathVariable String userId, @PathVariable String receiverId) {
        return new ResponseEntity<Message>(messageService.sendMessage(payload.get("content"), userId, receiverId),
                HttpStatus.CREATED);
    }
}
