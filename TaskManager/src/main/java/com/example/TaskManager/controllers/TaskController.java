package com.example.TaskManager.controllers;

import com.example.TaskManager.models.Task;
import com.example.TaskManager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public CompletableFuture<Task> createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public CompletableFuture<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    public CompletableFuture<List<Task>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/project/{projectId}")
    public CompletableFuture<List<Task>> getTasksByProjectId(@PathVariable Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    @GetMapping("/user/{userId}")
    public CompletableFuture<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }
}
