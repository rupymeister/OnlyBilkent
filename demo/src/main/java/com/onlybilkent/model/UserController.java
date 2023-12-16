package com.onlybilkent.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable String userId) {
        return new ResponseEntity<Optional<User>>(userService.singleUser(userId), HttpStatus.OK);
    }

    @PostMapping("/editUser/{userId}")
    public ResponseEntity<User> editUser(@RequestBody Map<String, String> payload, @PathVariable String userId) {
        if (payload == null || userId == null || !userService.existsById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userService.existsById(userId)) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(
                userService.editUser(userId, payload.get("name"), payload.get("surname"), payload.get("password"),
                        payload.get("bio")),
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

    // create new chat with given email
    @PostMapping("/createChat/{userId}")
    public ResponseEntity<Chat> createChat(@PathVariable String userId,
            @RequestParam("receiverId") String receiverId) {
        if (!userService.existsById(receiverId)) {
            return new ResponseEntity<Chat>(HttpStatus.NOT_FOUND);
        }
        // user should not be able to create a new chat if there is already one with the
        // receiver
        /**
         * if (chatService.existsBySenderIdAndReceiverId(userId, receiverId)) {
         * return new ResponseEntity<Chat>(HttpStatus.CONFLICT);
         * }
         **/

        return new ResponseEntity<Chat>(chatService.createChat(userId, receiverId), HttpStatus.OK);
    }

    // send message to given chat
    @PostMapping("/sendMessage/{userId}/{chatId}")
    public ResponseEntity<Message> sendMessage(@PathVariable String chatId, @PathVariable String userId,
            @RequestParam("content") String content) {

        if (!chatRepository.existsById(chatId)) {
            return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Message>(chatService.sendMessage(content, chatId, userId), HttpStatus.OK);
    }

    @GetMapping("{userId}/chats")
    public ResponseEntity<List<Chat>> getChats(@PathVariable String userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<List<Chat>>(HttpStatus.NOT_FOUND);
        }

        if (chatService.getChatsBySenderId(userId) == null) {
            return new ResponseEntity<List<Chat>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Chat>>(chatService.getChatsBySenderIdOrReceiverId(userId, userId),
                HttpStatus.OK);
    }

}