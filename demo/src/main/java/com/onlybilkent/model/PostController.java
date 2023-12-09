package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
    private UserService userService;

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
    public ResponseEntity<String> deletePost(@PathVariable ObjectId userId, @PathVariable String postId) {

        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND); // can later be modified
        }
        else if (!postService.existsById(postId)) {
            return new ResponseEntity<String>("Post not found.", HttpStatus.NOT_FOUND); // can later be modified
        }

        postService.deleteByPostId(postId, userId);
        return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/editPost/{postId}")
    public ResponseEntity<Post> editPost(@RequestBody Map<String, String> payload, @PathVariable String postId) {
        if (!postService.existsById(postId)) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Post>(postService.editPost(postId, payload.get("title"), payload.get("content")), HttpStatus.OK);
    }

    @PutMapping("/editIsPostActive/{postId}")
    public ResponseEntity<Post> editIsPostActive(@RequestBody Boolean isActive, @PathVariable String postId) {
        if (!postService.existsById(postId)) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Post>(postService.editIsPostActive(postId, isActive), HttpStatus.OK);
    }

    @GetMapping("/search/{str}")
    public ResponseEntity<Optional<Post>> getPostsByTitle(@PathVariable String str) {
        return new ResponseEntity<Optional<Post>>(postService.findByTitle(str), HttpStatus.OK);
    }

    @GetMapping("/search/{str}")
    public ResponseEntity<Optional<Post>> getPostsByContent(@PathVariable String str) {
        return new ResponseEntity<Optional<Post>>(postService.findByContent(str), HttpStatus.OK);
    }

    
}
