package com.example;

import com.example.model.User;
import com.example.services.PostService;
import com.example.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @BeforeEach
    void setUp() {
        UserService userService = new UserService();
        userService.clearUsers();
    }


    @Test
    public void eraseUserTest() {
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");
        assertEquals(1, userService.getAllUsers().size());
        userService.eraseUser(1L);
        assertEquals(0, userService.getAllUsers().size());
    }

    @Test
    public void getUserByNameTest() {
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");
        assertNotNull(userService.getUserByName("user1"));
        System.out.println(userService.getUserByName("user1"));
    }

    @Test
    public void getAllUsersTest() {
        UserService userService = new UserService();
        userService.createUser("user1", "user1@example.com");
        userService.createUser("user2", "user2@example.com");
        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        System.out.println(users);
    }

}
