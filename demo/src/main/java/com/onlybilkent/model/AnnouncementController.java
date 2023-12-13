package com.onlybilkent.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {
    
    @Autowired
    AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.allAnnouncements();
        return new ResponseEntity<List<Announcement>>(announcements, HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<Announcement>> getSinglePost(@PathVariable String title) {
        return new ResponseEntity<Optional<Announcement>>(announcementService.singlePost(title), HttpStatus.OK);
    }

    @GetMapping("/getSenderId/{id}")
    public ResponseEntity<String> getSenderId(@PathVariable String id) {
        return new ResponseEntity<String>(announcementService.getSenderId(id), HttpStatus.OK);
    }

    @GetMapping("/existsById/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        return new ResponseEntity<Boolean>(announcementService.existsById(id), HttpStatus.OK);
    }

    @GetMapping("/findByAnnouncementId/{id}")
    public ResponseEntity<Optional<Announcement>> findByAnnouncementId(@PathVariable String id) {
        return new ResponseEntity<Optional<Announcement>>(announcementService.findByAnnouncementId(id), HttpStatus.OK);
    }

    @GetMapping("/findBySenderId/{senderId}")
    public ResponseEntity<Optional<Announcement>> findBySenderId(@PathVariable String senderId) {
        return new ResponseEntity<Optional<Announcement>>(announcementService.findBySenderId(senderId), HttpStatus.OK);
    }


    @PostMapping(value = "/announce/{userId}", consumes = "application/json")
    public ResponseEntity<Announcement> announce(@PathVariable String userId, @RequestBody Map<String, String> payload) {
        Announcement announcement = announcementService.announce(userId, payload.get("title"), payload.get("content"));
        return new ResponseEntity<>(announcement, HttpStatus.CREATED);
    }

}
