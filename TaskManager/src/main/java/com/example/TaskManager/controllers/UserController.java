package com.example.TaskManager.controllers;


import com.example.TaskManager.models.User;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CompletableFuture<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public CompletableFuture<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public CompletableFuture<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public CompletableFuture<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/email/{email}")
    public CompletableFuture<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
