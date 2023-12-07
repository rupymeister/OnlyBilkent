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
public class PostService {

    @Autowired
    public PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> singlePost(String title) {
        return postRepository.findPostByTitle(title);
    }

    public Post createPost(String title, String content, String senderId) { // Lets use name for now later we can try id

        Post post = postRepository.insert(new Post(title, content, senderId));

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(senderId))
                .apply(new Update().push("postId").value(post))
                .first();
        return post;
    }

    public boolean existsById(String postId) {
        return postRepository.existsById(postId);
    }

    public void deleteByPostId(String postId, ObjectId userId) {
        ObjectId postIdObj = new ObjectId(postId); // I have converted but it might take ObjectId as a parameter too???
        postRepository.deleteById(postIdObj);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().pull("postId", postIdObj))
                .first();
    }

}
