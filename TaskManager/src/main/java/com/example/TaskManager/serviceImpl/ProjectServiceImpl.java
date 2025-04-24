package com.example.TaskManager.serviceImpl;

import com.example.TaskManager.models.Project;
import com.example.TaskManager.repositories.ProjectRepository;
import com.example.TaskManager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepo = projectRepository;
    }

    @Async
    @Override
    public CompletableFuture<Project> createProject(Project project) {
        return CompletableFuture.completedFuture(projectRepo.save(project));
    }

    @Async
    @Override
    public CompletableFuture<Project> updateProject(Long id, Project updatedProject) {
        Project existing = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existing.setCategory(updatedProject.getCategory());
        existing.setCreateDate(updatedProject.getCreateDate());

        return CompletableFuture.completedFuture(projectRepo.save(existing));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteProject(Long id) {
        projectRepo.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Project> getProjectById(Long id) {
        return CompletableFuture.completedFuture(
                projectRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Project not found"))
        );
    }

    @Async
    @Override
    public CompletableFuture<List<Project>> getAllProjects() {
        return CompletableFuture.completedFuture(projectRepo.findAll());
    }
}
