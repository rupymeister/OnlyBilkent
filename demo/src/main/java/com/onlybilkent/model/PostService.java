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
public class PostService {

    @Autowired
    public PostRepository postRepository;

    @Autowired
<<<<<<< HEAD
=======
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @Autowired
>>>>>>> cffaf3f35a4f79dfc6d2a1b9f1b28490364ef5d0
    private MongoTemplate mongoTemplate;

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> singlePost(String title) {
        return postRepository.findPostByTitle(title);
    }

    public Post createPost(String title, String content, String senderId) {

        Post post = postRepository.insert(new Post(title, content, senderId, true));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(senderId))
                .apply(new Update().push("postId").value(post))
                .first();

<<<<<<< HEAD
        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(senderId)), update, User.class);

        
=======
        ObjectId senderIdObj = new ObjectId(senderId);
        userService.postCountIncrement(senderIdObj);
>>>>>>> cffaf3f35a4f79dfc6d2a1b9f1b28490364ef5d0
        return post;
    }

    public void deleteByPostId(String postId, ObjectId userId) {
        ObjectId postIdObj = new ObjectId(postId); // I have converted but it might take ObjectId as a parameter too???
        postRepository.deleteById(postIdObj);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().pull("postId", postIdObj))
                .first();
    }

    public Post editPost(String postId, String newTitle, String newContent) {
        Post existingPost = postRepository.findById(postId);

        if (existingPost != null) {
            existingPost.setTitle(newTitle);
            existingPost.setContent(newContent);

            Post updatedPost = postRepository.save(existingPost);

            mongoTemplate.update(User.class)
                    .matching(Criteria.where("postId").is(postId))
                    .apply(new Update().set("postId.$.title", newTitle).set("postId.$.content", newContent))
                    .first();

            return updatedPost;
        } else {// if the post does not exist idk what to do

            return null;
        }
    }

    public Post editIsPostActive(String postId, Boolean isActive) {
        Post existingPost = postRepository.findById(postId);

        if (existingPost != null) {
            existingPost.setActive(isActive);

            Post updatedPost = postRepository.save(existingPost);

            mongoTemplate.update(User.class)
                    .matching(Criteria.where("postId").is(postId))
                    .apply(new Update().set("postId.$.isActive", isActive))
                    .first();

            return updatedPost;
        } else {// if the post does not exist idk what to do

            return null;
        }
    }

    public boolean existsById(String postId) {
        return postRepository.existsById(postId);
    }

    public Post findByPostId(String postId) {
        return postRepository.findById(postId);
    }

    public Optional<Post> findBySenderId(String senderId) {
        return postRepository.findBySenderId(senderId);
    }

    public Optional<Post> findByTitle(String str) {
        return postRepository.findByTitle(str);
    }

    public Optional<Post> findByContent(String str) {
        return postRepository.findByContent(str);
    }

}