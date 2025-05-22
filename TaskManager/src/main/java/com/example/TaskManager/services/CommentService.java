package com.example.TaskManager.services;

import com.example.TaskManager.models.Comment;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CommentService {
    CompletableFuture<Comment> createComment(Comment comment);
    CompletableFuture<Comment> updateComment(Long id, Comment updatedComment);
    CompletableFuture<Void> deleteComment(Long id);
    CompletableFuture<Comment> getCommentById(Long id);
    CompletableFuture<List<javax.xml.stream.events.Comment>> getAllComments();
    CompletableFuture<List<Comment>> getCommentsByTaskId(Long taskId);
    CompletableFuture<List<Comment>> getCommentsByUserId(Long userId);
}
