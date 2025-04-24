package com.example.TaskManager.services;

import com.example.TaskManager.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface TaskService {
    CompletableFuture<Task> createTask(Task task);
    CompletableFuture<Task> updateTask(Long id, Task task);
    CompletableFuture<Void> deleteTask(Long id);
    CompletableFuture<Task> getTaskById(Long id);
    CompletableFuture<List<Task>> getAllTasks();
    CompletableFuture<List<Task>> getTasksByProjectId(Long projectId);
    CompletableFuture<List<Task>> getTasksByUserId(Long userId);
}
