package com.example.TaskManager.controllers;

import com.example.TaskManager.models.Project;
import com.example.TaskManager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public CompletableFuture<Project> createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

    @GetMapping("/{id}")
    public CompletableFuture<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping
    public CompletableFuture<List<Project>> getAllProjects() {
        return projectService.getAllProjects();
    }
}
