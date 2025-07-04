package com.example.TaskManager;

import com.example.TaskManager.models.Project;
import com.example.TaskManager.repositories.ProjectRepository;
import com.example.TaskManager.serviceImpl.ProjectServiceImpl;
import com.example.TaskManager.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectServiceImplTest {

    @MockBean
    private ProjectRepository projectRepository;

    private final ProjectService projectService;

    public ProjectServiceImplTest() {
        this.projectRepository = Mockito.mock(ProjectRepository.class);
        this.projectService = new ProjectServiceImpl(projectRepository);
    }

    @Test
    void testGetProjectById() throws Exception {
        Project project = new Project();
        project.setId(1L);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(1L).get();

        assertNotNull(result);
    }
}
