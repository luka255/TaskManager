package com.example.TaskManager.repositories;

import com.example.TaskManager.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByAssignedToId(Long userId);
    Optional<Task> findById(Long id);
}
