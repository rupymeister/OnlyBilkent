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

    /**
     * @DeleteMapping("/delete/{studentId}/{postId}")
     * public String deletePost(@PathVariable ObjectId studentId, @PathVariable
     * ObjectId postId) {
     * Student student = studentRepository.findById(studentId).orElse(null);
     * 
     * if (student != null) {
     * List<Post> posts = student.getPosts();
     * Optional<Post> postToRemove = posts.stream()
     * .filter(post -> post.getId().equals(postId))
     * .findFirst();
     * 
     * if (postToRemove.isPresent()) {
     * posts.remove(postToRemove.get());
     * studentRepository.save(student);
     * return "Post with ID " + postId + " deleted for student with ID " +
     * studentId;
     * } else {
     * return "Post not found for deletion.";
     * }
     * } else {
     * return "Student not found.";
     * }
     * }
     **/

}
