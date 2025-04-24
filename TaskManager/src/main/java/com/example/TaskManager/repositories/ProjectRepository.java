package com.example.TaskManager.repositories;

import com.example.TaskManager.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCategory(String category);
}
