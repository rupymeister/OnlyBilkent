package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
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

    public Optional<Message> findMessageByContentRegex(String str) {
        return messageRepository.findByContentRegex(str);
    }

    public Message sendMessage(String content, String userId, String receiverId) {
        Message message = messageRepository.insert(new Message(userId, receiverId, content));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("messageId").value(message))
                .first();
        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(receiverId))
                .apply(new Update().push("messageId").value(message))
                .first();

        return message.getContent().equals(content) ? message: null;
    }

    public Optional<Message> getMessagesByUserId(String userId) {
        return messageRepository.findBySenderId(userId);
    }
    
    
    
}
