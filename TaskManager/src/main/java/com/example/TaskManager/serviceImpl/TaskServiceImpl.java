package com.example.TaskManager.serviceImpl;

import com.example.TaskManager.models.Task;
import com.example.TaskManager.repositories.TaskRepository;
import com.example.TaskManager.services.TaskService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepo;

    @Async
    @Override
    public CompletableFuture<Task> createTask(Task task) {
        return CompletableFuture.completedFuture(taskRepo.save(task));
    }

    @Async
    @Override
    public CompletableFuture<Task> updateTask(Long id, Task task) {
        Task existingTask = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setAssignedTo(task.getAssignedTo());
        existingTask.setProject(task.getProject());
        return CompletableFuture.completedFuture(taskRepo.save(existingTask));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteTask(Long id) {
        taskRepo.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Task> getTaskById(Long id) {
        return CompletableFuture.completedFuture(taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found")));
    }

    @Async
    @Override
    public CompletableFuture<List<Task>> getAllTasks() {
        return CompletableFuture.completedFuture(taskRepo.findAll());
    }

    @Async
    @Override
    public CompletableFuture<List<Task>> getTasksByProjectId(Long projectId) {
        return CompletableFuture.completedFuture(taskRepo.findByProjectId(projectId));
    }

    @Override
    public CompletableFuture<List<Task>> getTasksByUserId(Long userId) {
        return CompletableFuture.completedFuture(Collections.singletonList(taskRepo.findById(userId).orElseThrow(() -> new RuntimeException("Task not found"))));
    }
}