package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final UserService userService;
    private final MongoTemplate mongoTemplate;
    

    @Autowired
    public NotificationService(UserService userService, MongoTemplate mongoTemplate) {
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Notification> getAllNotifications() {
        return mongoTemplate.findAll(Notification.class);
    }

    public List<Notification> getNotificationsById(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Notification.class);
    }

    public Optional<Notification> getNotificationById(String notificationId) {
        return Optional.ofNullable(mongoTemplate.findById(notificationId, Notification.class));
    }

    public void markNotificationAsReadByID(String notificationId) {
        Query query = new Query(Criteria.where("_id").is(notificationId));
        Update update = new Update().set("isRead", true);
        mongoTemplate.updateFirst(query, update, Notification.class);
    }

    public void deleteNotificationByID(String notificationId) {
        Query query = new Query(Criteria.where("_id").is(notificationId));
        mongoTemplate.remove(query, Notification.class);
    }

    public Notification saveNotification(Notification notification) {
        return mongoTemplate.save(notification);
    }

    public Notification sendNotification(Notification notification) {
        Query query = new Query(Criteria.where("_id").is(notification.getUserId()));
        Update update = new Update().push("notifications", notification);
        mongoTemplate.updateFirst(query, update, User.class);
        return saveNotification(notification);
    }
}
