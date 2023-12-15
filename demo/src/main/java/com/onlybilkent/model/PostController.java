package com.onlybilkent.model;

import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/createPost1/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable String userId, @RequestBody Map<String, String> payload) {
        String postTypeString = payload.get("postType");
        
        try {
            Post.PostType postType = Post.PostType.valueOf(postTypeString);
            Post.Category category = Post.Category.valueOf(payload.get("category"));
            Post post = postService.createPostPhase1(userId, postType, payload.get("photoId"), category);
            System.out.println(post.getPhotoId());
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided postType is not a valid enum constant
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



   @PutMapping("/createLoanPost/{postId}")
    public ResponseEntity<Post> createLoanPost(@PathVariable String postId, @RequestBody Map<String, Object> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        LocalDate borrowUntilDate = LocalDate.parse((String) payload.get("borrowUntilDate")); // Assuming date is provided as a string
        int loanPricePerTime = (int) payload.get("loanPricePerTime");

        // Call the service method to create the loan post
        Post post = postService.createLoanPost(postId, title, content, borrowUntilDate, loanPricePerTime);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createBorrowPost/{postId}")
    public ResponseEntity<Post> createBorrowPost(@PathVariable String postId, @RequestBody Map<String, Object> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        LocalDate borrowUntilDate = LocalDate.parse((String) payload.get("borrowUntilDate")); // Assuming date is provided as a string

        // Call the service method to create the borrow post
        Post post = postService.createBorrowPost(postId, title, content, borrowUntilDate);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createSalePost/{postId}")
    public ResponseEntity<Post> createSalePost(@PathVariable String postId, @RequestBody Map<String, Object> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        double salePrice = (double) payload.get("salePrice");

        // Call the service method to create the sale post
        Post post = postService.createSalePost(postId, title, content, salePrice);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createFreePost/{postId}")
    public ResponseEntity<Post> createFreePost(@PathVariable String postId, @RequestBody Map<String, Object> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        boolean isFree = (boolean) payload.get("isFree");
        // Call the service method to create the free post
        Post post = postService.createFreePost(postId, title, content, isFree,  Post.PostType.FREE);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    
    @DeleteMapping("/deletePost/{userId}/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String userId, @PathVariable String postId) {

        if (!userService.existsById(userId)) {
            return new ResponseEntity<String>("User not found.", HttpStatus.NOT_FOUND); // can later be modified
        } else if (!postService.existsById(postId)) {
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
        return new ResponseEntity<Post>(postService.editPost(postId, payload.get("title"), payload.get("content")),
                HttpStatus.OK);
    }

    @PutMapping("/editIsPostActive/{postId}")
    public ResponseEntity<Post> editIsPostActive(@RequestBody Boolean isActive, @PathVariable String postId) {
        if (!postService.existsById(postId)) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Post>(postService.editIsPostActive(postId, isActive), HttpStatus.OK);
    }

    /**
    @PutMapping("/editPostImage/{postId}")
    public ResponseEntity<Post> editPostImage(@RequestBody byte[] imageData, @PathVariable String postId) {
        if (!postService.existsById(postId)) {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Post>(postService.editPostImage(postId, imageData), HttpStatus.OK);
    }
     */

    @GetMapping("/searchByTitle/{str}")
    public ResponseEntity<Optional<Post>> getPostsByTitle(@PathVariable String str) {
        Optional<Post> postOptional = postService.findByTitle(str);
        if(postOptional.isPresent()){
            return new ResponseEntity<>(postOptional, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByContent/{str}")
    public ResponseEntity<Optional<Post>> getPostsByContent(@PathVariable String str) {
        Optional<Post> postOptional = postService.findByContent(str);
        if (postOptional.isPresent()) {
            return new ResponseEntity<>(postOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}