package com.onlybilkent.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
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

    /**
     * public String createChat(String senderId, String receiverId) {
     * Chat chat = new Chat(senderId, receiverId);
     * chatRepository.insert(chat);
     * 
     * mongoTemplate.update(User.class)
     * .matching(Criteria.where("id").is(senderId))
     * .apply(new Update().push("chats").value(chat))
     * .first();
     * 
     * mongoTemplate.update(User.class)
     * .matching(Criteria.where("id").is(receiverId))
     * .apply(new Update().push("chats").value(chat))
     * .first();
     * 
     * return chat.getChatId().toString();
     * }
     **/

    public Chat createChat(String senderId, String receiverId) {
        Chat chat = new Chat(senderId, receiverId);
        chatRepository.save(chat);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(senderId))
                .apply(new Update().push("chatId").value(chat))
                .first();

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(receiverId))
                .apply(new Update().push("chatId").value(chat))
                .first();

        return chat;
    }

    public Message sendMessage(String content, String userId, String receiverId) {
        Message message = messageRepository.insert(new Message(userId, receiverId, content));

        mongoTemplate.update(User.class)
                .matching(new Criteria().andOperator(
                        Criteria.where("userId").is(userId),
                        Criteria.where("chats.receiverId").is(receiverId)))
                .apply(new Update().push("chats.$[elem].messages").value(message))
                .first();

        mongoTemplate.update(User.class)
                .matching(new Criteria().andOperator(
                        Criteria.where("userId").is(userId),
                        Criteria.where("chats.receiverId").is(receiverId)))
                .apply(new Update().push("chats.$[elem].messages").value(message))
                .first();

        return message;
    }

    public void markNotificationAsReadByID() {
    }

    public void allMessages() {
    }

    public boolean existsBySenderIdAndReceiverId(String userId, String receiverId) {
        return chatRepository.existsBySenderIdAndReceiverId(userId, receiverId);
    }

}