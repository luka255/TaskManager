package com.example.TaskManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    // Relationships
    @OneToMany(mappedBy = "assignedTo")
    private List<Task> tasks;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
