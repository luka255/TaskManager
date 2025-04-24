package com.example.TaskManager.services;

import com.example.TaskManager.models.Project;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ProjectService {
    CompletableFuture<Project> createProject(Project project);
    CompletableFuture<Project> updateProject(Long id, Project project);
    CompletableFuture<Void> deleteProject(Long id);
    CompletableFuture<Project> getProjectById(Long id);
    CompletableFuture<List<Project>> getAllProjects();
}
