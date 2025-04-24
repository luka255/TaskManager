package com.example.TaskManager.serviceImpl;

import com.example.TaskManager.models.User;
import com.example.TaskManager.repositories.UserRepository;
import com.example.TaskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepos;

    @Async
    @Override
    public CompletableFuture<User> createUser(User user) {
        return CompletableFuture.completedFuture(userRepos.save(user));
    }

    @Async
    @Override
    public CompletableFuture<User> updateUser(Long id, User user) {
        User existingUser = userRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return CompletableFuture.completedFuture(userRepos.save(existingUser));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteUser(Long id) {
        userRepos.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<User> getUserById(Long id) {
        return CompletableFuture.completedFuture(userRepos.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Async
    @Override
    public CompletableFuture<List<User>> getAllUsers() {
        return CompletableFuture.completedFuture(userRepos.findAll());
    }

    @Async
    @Override
    public CompletableFuture<User> getUserByEmail(String email) {
        return CompletableFuture.completedFuture(userRepos.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }
}
