package com.onlybilkent.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    private final StudentRepository studentRepository;

    // Constructor injection for StudentRepository
    @Autowired
    public PostController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<>(postService.allPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getSinglePost(@PathVariable ObjectId id) {
        return new ResponseEntity<>(postService.singlePost(id), HttpStatus.OK);
    }

    @PostMapping("/create/{studentId}")
    public String createPost(@PathVariable ObjectId studentId, @RequestBody Post post) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null && student.getCanPost()) {
            student.addPost(post);
            studentRepository.save(student);
            return "Post created by student with ID: " + studentId;
        } else {
            return "Unable to create post. Student not found or doesn't have permission.";
        }
    }

    @DeleteMapping("/delete/{studentId}/{postId}")
    public String deletePost(@PathVariable ObjectId studentId, @PathVariable ObjectId postId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            List<Post> posts = student.getPosts();
            Optional<Post> postToRemove = posts.stream()
                    .filter(post -> post.getId().equals(postId))
                    .findFirst();

            if (postToRemove.isPresent()) {
                posts.remove(postToRemove.get());
                studentRepository.save(student);
                return "Post with ID " + postId + " deleted for student with ID " + studentId;
            } else {
                return "Post not found for deletion.";
            }
        } else {
            return "Student not found.";
        }
    }
}
