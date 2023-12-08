package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.allPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<Post>> getSinglePost(@PathVariable String title) {
        return new ResponseEntity<Optional<Post>>(postService.singlePost(title), HttpStatus.OK);
    }

    @PostMapping("/createPost/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> payload, @PathVariable String userId) {
        return new ResponseEntity<Post>(postService.createPost(payload.get("title"), payload.get("content"), userId),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePost/{userId}/{postId}")
    public String deletePost(@PathVariable String userId, @PathVariable String postId) {
        User user = userRepository.findUserById(userId).orElse(null);
        if (user != null) {
            List<Post> posts = user.getPostId();
            Optional<Post> postToRemove = posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst();

            if (postToRemove.isPresent()) {
                posts.remove(postToRemove.get());
                userRepository.save(user);
                return "Post with ID " + postId + " deleted for user with ID " + userId;
            } else {
                return "Post not found for deletion.";
            }
        } else {
            return "User not found.";
        }
    }

}
