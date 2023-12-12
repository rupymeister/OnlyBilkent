package com.onlybilkent.model;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, ObjectId> {
    @Query("{ 'content' : { '$regex' : ?0 , $options: 'i'}}")
    Optional<Message> findByContentRegex(String str);

    boolean existsById(ObjectId messageId);
    
    @Override
    <S extends Message> S save(S entity);

    @Override
    Optional<Message> findById(ObjectId messageId);
 
    Optional<Message> findBySenderId(String userId);

}