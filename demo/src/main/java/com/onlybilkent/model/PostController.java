package com.onlybilkent.model;

import java.util.Map;
import java.util.Optional;
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

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable String postId) {
        Post post = postService.findByPostId(postId);
        if (post != null) {
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable Category category) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/searchByTitle/{str}")
    public ResponseEntity<List<Post>> getPostsByTitle(@PathVariable String str) {
        List<Post> posts = postService.findByTitle(str);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByTitle/{str}")
    public ResponseEntity<List<Post>> getCategoryPostsByTitle(@PathVariable Category category,
            @PathVariable String str) {
        List<Post> posts = postService.findByContent(str);
        List<Post> filteredPosts = posts.stream()
                .filter(post -> post.isActive() == true && post.getCategory() == category).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/searchByContent/{str}")
    public ResponseEntity<List<Post>> getPostsByContent(@PathVariable String str) {
        List<Post> posts = postService.findByContent(str);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByContent/{str}")
    public ResponseEntity<List<Post>> getCategoryPostsByContent(@PathVariable Category category,
            @PathVariable String str) {
        List<Post> posts = postService.findByContent(str);
        List<Post> filteredPosts = posts.stream()
                .filter(post -> post.isActive() == true && post.getCategory() == category).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPrice/{price}")
    public ResponseEntity<List<Post>> getCategoryPostsByPrice(@PathVariable Category category,
            @PathVariable int price) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true && post.getPrice() == price)
                .collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPriceRange/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Post>> getPostsByPriceRange(@PathVariable Category category, @PathVariable int minPrice,
            @PathVariable int maxPrice) {
        List<Post> postList = postService.findByPriceBetween(minPrice, maxPrice);
        List<Post> filteredPosts = postList.stream()
                .filter(post -> post.isActive() == true && post.getCategory() == category).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPriceLessThanEqual/{price}")
    public ResponseEntity<List<Post>> getCategoryPostsByPriceLessThanEqual(@PathVariable Category category,
            @PathVariable int price) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true && post.getPrice() <= price)
                .collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{category}/searchByPriceGreaterThanEqual/{price}")
    public ResponseEntity<List<Post>> getCategoryPostsByPriceGreaterThanEqual(@PathVariable Category category,
            @PathVariable int price) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true && post.getPrice() >= price)
                .collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // loan
    @GetMapping("/loan")
    public ResponseEntity<List<Post>> getLoanPosts() {
        List<Post> posts = postService.findByPostType(Post.PostType.LOAN);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
    }

    // sale
    @GetMapping("/sale")
    public ResponseEntity<List<Post>> getSalePosts() {
        List<Post> posts = postService.findByPostType(Post.PostType.SALE);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
    }

    // free
    @GetMapping("/free")
    public ResponseEntity<List<Post>> getFreePosts() {
        List<Post> posts = postService.findByPostType(Post.PostType.FREE);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
    }

    // found
    @GetMapping("/found")
    public ResponseEntity<List<Post>> getFoundPosts() {
        List<Post> posts = postService.findByPostType(Post.PostType.FOUND);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
    }

    // lost
    @GetMapping("/lost")
    public ResponseEntity<List<Post>> getLostPosts() {
        List<Post> posts = postService.findByPostType(Post.PostType.LOST);
        List<Post> filteredPosts = posts.stream().filter(post -> post.isActive() == true).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        }
        return new ResponseEntity<>(posts, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{category}/getByPostType/{postType}")
    public ResponseEntity<List<Post>> getCategoryPostsByPostType(@PathVariable Category category,
            @PathVariable Post.PostType postType) {
        List<Post> posts = postService.findByCategory(category);
        List<Post> filteredPosts = posts.stream()
                .filter(post -> post.isActive() == true && post.getPostType() == postType).collect(Collectors.toList());
        if (!filteredPosts.isEmpty()) {
            return new ResponseEntity<>(filteredPosts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> create(@PathVariable String userId, @RequestBody Map<String, String> payload) {
        String postTypeString = payload.get("postType");

        try {
            Post.PostType postType = Post.PostType.valueOf(postTypeString);
            // Post.Category category = Post.Category.valueOf(payload.get("category"));
            Post post = postService.create(postType, userId);
            return new ResponseEntity<>(post.getId().toString(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided postType is not a valid enum constant
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @PostMapping("/createPost1/{userId}")
     * public ResponseEntity<Post> createPost(@PathVariable String
     * userId, @RequestBody Map<String, String> payload) {
     * String postTypeString = payload.get("postType");
     * 
     * try {
     * Post.PostType postType = Post.PostType.valueOf(postTypeString);
     * Post.Category category = Post.Category.valueOf(payload.get("category"));
     * Post post = postService.createPostPhase1(userId, postType,
     * payload.get("photoId"), category);
     * System.out.println(post.getPhotoId());
     * return new ResponseEntity<>(post, HttpStatus.CREATED);
     * } catch (IllegalArgumentException e) {
     * // Handle the case where the provided postType is not a valid enum constant
     * return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     * }
     * }
     **/

    @PutMapping("/createLoanPost/{postId}")
    public ResponseEntity<Post> createLoanPost(@PathVariable String postId, @RequestBody Map<String, String> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        Category category = Category.valueOf(payload.get("category"));
        int loanPricePerTime = Integer.parseInt(payload.get("loanPricePerTime"));

        // Call the service method to create the loan post
        Post post = postService.createLoanPost(postId, title, category, content, loanPricePerTime);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createSalePost/{postId}")
    public ResponseEntity<Post> createSalePost(@PathVariable String postId, @RequestBody Map<String, String> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        Category category = Category.valueOf(payload.get("category"));
        double salePrice = Double.parseDouble(payload.get("salePrice"));

        // Call the service method to create the sale post
        Post post = postService.createSalePost(postId, title, category, content, salePrice);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createFreePost/{postId}")
    public ResponseEntity<Post> createFreePost(@PathVariable String postId, @RequestBody Map<String, String> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        Category category = Category.valueOf((String) payload.get("category"));
        // Call the service method to create the free post
        Post post = postService.createFreePost(postId, title, category, content);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createFoundPost/{postId}")
    public ResponseEntity<Post> createFoundPost(@PathVariable String postId, @RequestBody Map<String, String> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        Category category = Category.valueOf((String) payload.get("category"));
        // Call the service method to create the found post
        Post post = postService.createFoundPost(postId, title, category, content);

        // Return the response
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/createLostPost/{postId}")
    public ResponseEntity<Post> createLostPost(@PathVariable String postId, @RequestBody Map<String, String> payload) {
        // Extracting payload values
        String title = (String) payload.get("title");
        String content = (String) payload.get("content");
        Category category = Category.valueOf((String) payload.get("category"));
        // Call the service method to create the lost post
        Post post = postService.createLostPost(postId, title, category, content);

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
     * @PutMapping("/editPostImage/{postId}")
     * public ResponseEntity<Post> editPostImage(@RequestBody byte[]
     * imageData, @PathVariable String postId) {
     * if (!postService.existsById(postId)) {
     * return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
     * }
     * return new ResponseEntity<Post>(postService.editPostImage(postId, imageData),
     * HttpStatus.OK);
     * }
     */

}