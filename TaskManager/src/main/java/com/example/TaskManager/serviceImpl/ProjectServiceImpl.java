package com.example.TaskManager.serviceImpl;

import com.example.TaskManager.models.Project;
import com.example.TaskManager.repositories.ProjectRepository;
import com.example.TaskManager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepo = projectRepository;
    }

    @Async
    @Override
    public CompletableFuture<Project> createProject(Project project) {
        logger.info("Creating project: {}", project);
        return CompletableFuture.completedFuture(projectRepo.save(project));
    }

    @Async
    @Override
    public CompletableFuture<Project> updateProject(Long id, Project updatedProject) {
        logger.info("Updating project with ID: {}", id);
        Project existing = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        existing.setCategory(updatedProject.getCategory());
        existing.setCreateDate(updatedProject.getCreateDate());

        return CompletableFuture.completedFuture(projectRepo.save(existing));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteProject(Long id) {
        logger.info("Deleting project with ID: {}", id);
        projectRepo.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Project> getProjectById(Long id) {
        logger.info("Fetching project with ID: {}", id);
        return CompletableFuture.completedFuture(
                projectRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Project not found"))
        );
    }

    @Async
    @Override
    public CompletableFuture<List<Project>> getAllProjects() {
        logger.info("Fetching all projects");
        return CompletableFuture.completedFuture(projectRepo.findAll());
    }
}
