package com.example.TaskManager.serviceImpl;

import com.example.TaskManager.models.Comment;
import com.example.TaskManager.repositories.CommentRepository;
import com.example.TaskManager.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Async
    @Override
    public CompletableFuture<Comment> createComment(Comment comment) {
        return CompletableFuture.completedFuture(commentRepository.save(comment));
    }

    @Async
    @Override
    public CompletableFuture<Comment> updateComment(Long id, Comment updatedComment) {
        Comment existing = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        existing.setText(updatedComment.getText());
        existing.setCreateDate(updatedComment.getCreateDate());
        existing.setUser(updatedComment.getUser());
        existing.setTask(updatedComment.getTask());

        return CompletableFuture.completedFuture(commentRepository.save(existing));
    }

    @Async
    @Override
    public CompletableFuture<Void> deleteComment(Long id) {
        commentRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async
    @Override
    public CompletableFuture<Comment> getCommentById(Long id) {
        return CompletableFuture.completedFuture(
                commentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Comment not found"))
        );
    }

    @Async
    @Override
    public CompletableFuture<List<Comment>> getAllComments() {
        return CompletableFuture.completedFuture(commentRepository.findAll());
    }

    @Async
    @Override
    public CompletableFuture<List<Comment>> getCommentsByTaskId(Long taskId) {
        return CompletableFuture.completedFuture(commentRepository.findById(taskId));
    }

    @Async
    @Override
    public CompletableFuture<List<Comment>> getCommentsByUserId(Long userId) {
        return CompletableFuture.completedFuture(commentRepository.findByUserId(userId));
    }
}