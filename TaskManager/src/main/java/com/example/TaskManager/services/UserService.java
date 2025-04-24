package com.example.TaskManager.services;

import com.example.TaskManager.models.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    CompletableFuture<User> createUser(User user);
    CompletableFuture<User> updateUser(Long id, User user);
    CompletableFuture<Void> deleteUser(Long id);
    CompletableFuture<User> getUserById(Long id);
    CompletableFuture<List<User>> getAllUsers();
    CompletableFuture<User> getUserByEmail(String email);
}
