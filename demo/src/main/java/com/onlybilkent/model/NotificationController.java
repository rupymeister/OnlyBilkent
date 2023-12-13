package com.onlybilkent.model;

import com.onlybilkent.model.registration.RegistrationRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Controller class for Notifications,
 * specifying end points and related functionalities
 */
@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController( NotificationService notificationService ) {
        this.notificationService = notificationService;
    }

    //write send notification using mongodb here
    @PostMapping("/sendNotification")
    public Notification sendNotification(@RequestBody Map<String, String> payload) {
        Notification notification = new Notification(payload.get("userId"), payload.get("message"), payload.get(""));
        return notificationService.sendNotification(notification);
    }

    @PostMapping("/markRead/{id}")
    public void markNotificationAsReadByID(@PathVariable("id") String id) {
        notificationService.markNotificationAsReadByID(id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable("id") String id) {
        notificationService.deleteNotificationByID(id);
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable("id") String id ) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }

    @GetMapping("/user/{id}")
    public  List<Notification> getNotificationsOfUserByUserId(@PathVariable("id") String id ) {
        return notificationService.getNotificationsOfUserByUserId(id);
    }
}