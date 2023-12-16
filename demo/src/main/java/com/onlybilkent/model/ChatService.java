package com.onlybilkent.model;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    public UserService userService;
    @Autowired
    public ChatRepository chatRepository;
    @Autowired
    public MessageService messageService;
    @Autowired
    public MessageRepository messageRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public String createChat(String senderId, String receiverId) {
        Chat chat = new Chat(senderId, receiverId);
        chatRepository.insert(chat);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(senderId))
                .apply(new Update().push("chats").value(chat))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(receiverId))
                .apply(new Update().push("chats").value(chat))
                .first();

        return chat.getChatId().toString();
    }

    public Message sendMessage(String content, String userId, String receiverId) {
        Message message = messageRepository.insert(new Message(userId, receiverId, content));

        Query query = new Query(Criteria.where("userId").is(userId).and("chats.receiverId").is(receiverId));
        Update update = new Update().push("chats.$[elem].messages", message);
        mongoTemplate.updateFirst(query, update, User.class);

        query = new Query(Criteria.where("userId").is(receiverId).and("chats.receiverId").is(userId));
        update = new Update().push("chats.$[elem].messages", message);
        mongoTemplate.updateFirst(query, update, User.class);
        
        return message;
    }

    public List<Message> getMessages(String userId, String receiverId) {
        Query query = new Query(Criteria.where("userId").is(userId).and("chats.receiverId").is(receiverId));
        User user = mongoTemplate.findOne(query, User.class);
        List<Chat> chats = user.getChats();
        for (Chat chat : chats) {
            if (chat.getReceiverId().equals(receiverId)) {
                return chat.getMessages();
            }
        }
        return null;
    }

    public void allMessages() {
    }
    
}
