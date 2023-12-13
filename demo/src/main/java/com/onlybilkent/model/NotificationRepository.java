package com.onlybilkent.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByUserId(String userId);

    void deleteById(String id);

    Optional<Notification> findById(String notificationId);

    @Query(value = "{'NotificationId': ?0}", exists = true)
    boolean existsById(String notificationId);


}
