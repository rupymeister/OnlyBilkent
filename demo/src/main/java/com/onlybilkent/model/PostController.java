package com.onlybilkent.model;

import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlybilkent.model.Post.Category;

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

    @GetMapping
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable Category category) {
        List<Post> posts = postService.findByCategory(category);
        if(!posts.isEmpty()){
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
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
    public ResponseEntity<List<Post>> getPostsByTitle(@PathVariable String str) {
        List<Post> postList = postService.findByTitle(str);
        if(!postList.isEmpty()){
            return new ResponseEntity<>(postList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{category}/searchByTitle/{str}")
    public ResponseEntity<List<Post>> getCategoryPostsByTitle(@PathVariable Category category, @PathVariable String str) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.getTitle().contains(str)).collect(Collectors.toList());
        if(!filteredPosts.isEmpty()){
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/searchByContent/{str}")
    public ResponseEntity<List<Post>> getPostsByContent(@PathVariable String str) {
        List<Post> postList = postService.findByContent(str);
        if (!postList.isEmpty()) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByContent/{str}")
    public ResponseEntity<List<Post>> getCategoryPostsByContent(@PathVariable Category category,@PathVariable String str) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.getTitle().contains(str)).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPrice/{price}")
    public ResponseEntity<List<Post>> getCategoryPostsByPrice(@PathVariable Category category,@PathVariable int price) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.getPrice() == price).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByPriceRange/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Post>> getPostsByPriceRange(@PathVariable int minPrice, @PathVariable int maxPrice) {
        List<Post> postList = postService.findByPriceBetween(minPrice, maxPrice);
        if (!postList.isEmpty()) {
            return new ResponseEntity<>(postList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPriceLessThanEqual/{price}")
    public ResponseEntity<List<Post>> getCategoryPostsByPriceLessThanEqual(@PathVariable Category category,@PathVariable int price) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.getPrice() <= price).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPriceGreaterThanEqual/{price}")
    public ResponseEntity<List<Post>> getCategoryPostsByPriceGreaterThanEqual(@PathVariable Category category,@PathVariable int price) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.getPrice() >= price).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/getByPostType/{postType}")
    public ResponseEntity<List<Post>> getCategoryPostsByPostType(@PathVariable Category category,@PathVariable Post.PostType postType) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.getPostType() == postType).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}