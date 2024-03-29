package com.onlybilkent.model;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.onlybilkent.model.Post.Category;
import com.onlybilkent.model.Post.PostType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.List;

@Service
public class PostService {

    @Autowired
    public PostRepository postRepository;

    @Autowired
    public UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> singlePost(String title) {
        return postRepository.findPostByTitle(title);
    }

    public Post createPostPhase1(String senderId, PostType postType, String photoId, Category category) {
        Post post = postRepository.insert(new Post(senderId, postType, photoId, category));
        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(senderId))
                .apply(new Update().push("postId").value(post))
                .first();

        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(senderId)), update, User.class);
        return post;
    }

    public Post createLoanPost(String postId, String title, Category category, String content,
            double loanPricePerTime) {
        Post post = postRepository.findById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setActive(true);
        post.setLoanPricePerTime(loanPricePerTime);
        postRepository.save(post);

        mongoTemplate.update(Post.class)
                .matching(Criteria.where("id").is(findByPostId(postId)))
                .apply(new Update().push("postId").value(post))
                .first();
        return post;
    }

    public Post createSalePost(String postId, String title, Category category, String content, double salePrice) {
        Post post = postRepository.findById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setActive(true);
        postRepository.save(post);
        post.setSalePrice(salePrice);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(getSenderId(postId)))
                .apply(new Update().push("postId").value(post))
                .first();

        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(getSenderId(postId))), update, User.class);
        return post;
    }

    public Post createFreePost(String postId, String title, Category category, String content) {
        Post post = postRepository.findById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setActive(true);
        postRepository.save(post);
        post.setFree(true);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(getSenderId(postId)))
                .apply(new Update().push("postId").value(post))
                .first();

        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(getSenderId(postId))), update, User.class);
        return post;
    }

    public Post createFoundPost(String postId, String title, Category category, String content) {

        Post post = postRepository.findById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setActive(true);
        postRepository.save(post);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(getSenderId(postId)))
                .apply(new Update().push("postId").value(post))
                .first();

        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(getSenderId(postId))), update, User.class);
        return post;
    }

    public Post createLostPost(String postId, String title, Category category, String content) {
        Post post = postRepository.findById(postId);
        post.setTitle(title);
        post.setContent(content);
        post.setActive(true);
        postRepository.save(post);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(getSenderId(postId)))
                .apply(new Update().push("postId").value(post))
                .first();

        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(getSenderId(postId))), update, User.class);
        return post;
    }

    /**
     * public Post createPost(String title, String content, String senderId,
     * PostType postType) {
     * 
     * Post post = postRepository.insert(new Post(title, content, senderId, true,
     * postType));
     * 
     * if(postType == PostType.BORROW) {
     * createBorrowPost(senderId, title, content, null, postType);
     * } else if(postType == PostType.SALE) {
     * createSalePost(senderId, title, content, 0, postType);
     * } else if(postType == PostType.FREE) {
     * createFreePost(senderId, title, content, true, postType);
     * }
     * else{
     * createLoanPost(senderId, title, content, null, 0, postType);
     * }
     * mongoTemplate.update(User.class)
     * .matching(Criteria.where("id").is(senderId))
     * .apply(new Update().push("postId").value(post))
     * .first();
     * 
     * Update update = new Update().inc("postCount", 1);
     * mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(senderId)),
     * update, User.class);
     * 
     * return post;
     * }
     * 
     */
    public void deleteByPostId(String postId, String userId) {
        ObjectId postIdObj = new ObjectId(postId); // I have converted but it might take ObjectId as a parameter too???
        postRepository.deleteById(postIdObj);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().pull("postId", postIdObj))
                .first();
    }

    public Post editPost(String postId, String newTitle, String newContent) {
        Post existingPost = postRepository.findById(postId);

        if (newTitle != null && !newTitle.isEmpty()) {
            existingPost.setTitle(newTitle);
        }
        if (newContent != null && !newContent.isEmpty()) {
            existingPost.setContent(newContent);
        }

        Post updatedPost = postRepository.save(existingPost);

        Update update = new Update();
        if (newTitle != null && !newTitle.isEmpty()) {
            update.set("postId.$.title", newTitle);
        }
        if (newContent != null && !newContent.isEmpty()) {
            update.set("postId.$.content", newContent);
        }

        mongoTemplate.update(User.class)
                .matching(Criteria.where("postId").is(postId))
                .apply(update)
                .first();

        return updatedPost;
    }

    public Post editIsPostActive(String postId, Boolean isActive) {
        Post existingPost = postRepository.findById(postId);

        existingPost.setActive(isActive);

        Post updatedPost = postRepository.save(existingPost);

        mongoTemplate.update(User.class)
                .matching(Criteria.where("postId").is(postId))
                .apply(new Update().set("postId.$.isActive", isActive))
                .first();

        return updatedPost;

    }

    public String getSenderId(String postId) {
        Post post = postRepository.findById(postId);
        return post.getSenderId();
    }

    public boolean existsById(String postId) {
        return postRepository.existsById(postId);
    }

    public Post findByPostId(String postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findBySenderId(String senderId) {
        return postRepository.findBySenderId(senderId);
    }

    public List<Post> findByTitle(String str) {
        return postRepository.findByTitleRegex(str);
    }

    public List<Post> findByContent(String str) {
        return postRepository.findByContentRegex(str);
    }

    public List<Post> findByPrice(int price) {
        List<Post> postsAtOrBelowPrice = postRepository.findByPriceLessThanEqual(price);
        return postsAtOrBelowPrice;
    }

    public List<Post> findByPriceBetween(int minPrice, int maxPrice) {
        return postRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Post> findByCategory(Category category) {
        return postRepository.findByCategory(category);
    }

    public Post getPost(String postId) {
        return postRepository.findById(postId);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post create(PostType postType, String userId) {
        Post post = postRepository.save(new Post(postType, userId));
        mongoTemplate.update(User.class)
                .matching(Criteria.where("id").is(userId))
                .apply(new Update().push("postId").value(post))
                .first();

        Update update = new Update().inc("postCount", 1);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(userId)), update, User.class);
        return post;
    }

    public List<Post> findByPostType(PostType loan) {
        return postRepository.findByPostType(loan);
    }

}