package com.example;

import com.example.model.Post;
import com.example.services.CategoryService;
import com.example.services.PostService;
import com.example.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostServiceTest {


    @Test
    public void getAllPostsTest() {
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");
        userService.createUser("user2", "user2@example.com");

        PostService postService = new PostService();
        postService.createPost("post1", 1L);
        postService.createPost("post2", 1L);
        postService.createPost("post3", 2L);
        postService.createPost("post4", 2L);


        List<Post> posts = postService.getAllPosts();

        System.out.println(posts);

        assertEquals(4, posts.size());
    }

    @Test
    public void getPostsByUserTest() {
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");
        userService.createUser("user2", "user2@example.com");

        PostService postService = new PostService();
        postService.createPost("post1", 1L);
        postService.createPost("post2", 1L);
        postService.createPost("post3", 2L);
        postService.createPost("post4", 2L);

        List<Post> posts = postService.getPostsByUser(2L);

        System.out.println(posts);

        assertEquals(2, posts.size());
    }

    @Test
    public void getPostByIdTest (){
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");

        PostService postService = new PostService();
        postService.createPost("post1", 1L);

        Post post = postService.getPostById(1L);

        assertEquals("Post{id=1, content='post1', author=user1}", post.toString());

    }

    @Test
    public void getPostsByCategoryTest() {
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");
        userService.createUser("user2", "user2@example.com");

        PostService postService = new PostService();
        postService.createPost("post1", 1L);
        postService.createPost("post2", 1L);
        postService.createPost("post3", 2L);
        postService.createPost("post4", 2L);

        CategoryService categoryService = new CategoryService();
        categoryService.createCategory("category1");
        categoryService.createCategory("category2");
        categoryService.createCategory("category3");

        postService.addCategoryToPost(1L, 1L);
        postService.addCategoryToPost(1L, 2L);
        postService.addCategoryToPost(2L, 1L);
        postService.addCategoryToPost(3L, 3L);

        List<Post> posts = postService.getPostsByCategory(1L);
        System.out.println(posts);
        assertEquals(2, posts.size());

    }

}
