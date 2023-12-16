package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    public MessageRepository messageRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Message> allMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessage(ObjectId id) {
        return messageRepository.findById(id);
    }

    public boolean existsById(ObjectId messageId) {
        return messageRepository.existsById(messageId);
    }

    public Optional<Message> getMessagesByUserId(String userId) {
        return messageRepository.findBySenderId(userId);
    }

    public void markNotificationAsReadByID(String messageId) {
        Query query = new Query(Criteria.where("_id").is(messageId));
        Update update = new Update().set("isRead", true);
        mongoTemplate.updateFirst(query, update, Message.class);
    }
    

}
