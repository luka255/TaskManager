package com.example.TaskManager.controllers;

import com.example.TaskManager.models.Comment;
import com.example.TaskManager.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public CompletableFuture<Comment> createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public CompletableFuture<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<Void> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @GetMapping("/{id}")
    public CompletableFuture<Comment> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping
    public CompletableFuture<List<Comment>> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/task/{taskId}")
    public CompletableFuture<List<Comment>> getCommentsByTaskId(@PathVariable Long taskId) {
        return commentService.getCommentsByTaskId(taskId);
    }

    @GetMapping("/user/{userId}")
    public CompletableFuture<List<Comment>> getCommentsByUserId(@PathVariable Long userId) {
        return commentService.getCommentsByUserId(userId);
    }
}