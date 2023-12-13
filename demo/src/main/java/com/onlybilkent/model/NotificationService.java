package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    
    @Autowired
    public UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotificationsByUserId(String userId) {
        return notificationRepository.findAllById(userId);
    }

    public Notification deleteNotification(Notification notification) {
        notificationRepository.delete(notification);
        return notification;
    }

    //write get notification by id
    public Notification getNotification(String id) {
        return notificationRepository.findById(id).orElseThrow(() -> new IllegalStateException("Notification with id:" + id + " doesn't exist!"));
    }

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationsOfUserByUserId( String id ) {
        return notificationRepository.findAllById(id);
    }


    public Notification markNotificationAsRead(String id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new IllegalStateException("Notification with id:" + id + " doesn't exist!"));
        notification.setRead(true);
        notificationRepository.save(notification);
        return notification;
    }

    public void deleteNotificationByID(String id) {
        boolean exists = notificationRepository.existsById(id);
        if ( !exists ) {
            throw new IllegalStateException("Notification with id:" + id + " doesn't exist!");
        }
        notificationRepository.deleteById(id);
    }

    public Notification getNotificationById(String id) {
        return notificationRepository.findById(id).orElseThrow(() -> new IllegalStateException("Notification with id:" + id + " doesn't exist!"));
    }

    public void markNotificationAsReadByID(String id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new IllegalStateException("Notification with id:" + id + " doesn't exist!"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}