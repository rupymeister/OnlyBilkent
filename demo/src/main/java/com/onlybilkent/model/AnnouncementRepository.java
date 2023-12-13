package com.onlybilkent.model;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AnnouncementRepository extends MongoRepository<Announcement, String>{
    Optional<Announcement> findAnnouncementByTitle(String title);

    boolean existsById(String announcementId);

    Optional<Announcement> findBySenderId(String senderId);

    @Query("{ 'title' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Announcement> findByTitleRegex(String str);
    
    @Query("{ 'content' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Announcement> findByContentRegex(String str);

    @Query("{ 'price' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Announcement> findByPriceRegex(int price);

    @Override 
    <S extends Announcement> S save(S entity);

    Optional<Announcement> findPostByTitle(String title);

}
