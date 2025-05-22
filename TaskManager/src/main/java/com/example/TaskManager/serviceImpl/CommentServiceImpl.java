package com.example.TaskManager.serviceImpl;

import com.example.TaskManager.models.Comment;
import com.example.TaskManager.repositories.CommentRepository;
import com.example.TaskManager.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Async
    @Override
    @Transactional
    public CompletableFuture<Comment> createComment(Comment comment) {
        return CompletableFuture.supplyAsync(() -> {
            if (comment == null) {
                throw new IllegalArgumentException("Comment cannot be null");
            }
            return commentRepository.save(comment);
        });
    }

    @Async
    @Override
    @Transactional
    public CompletableFuture<Comment> updateComment(Long id, Comment updatedComment) {
        return CompletableFuture.supplyAsync(() -> {
            if (updatedComment == null) {
                throw new IllegalArgumentException("Updated comment data cannot be null");
            }

            Comment existingComment = (Comment) commentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comment with ID " + id + " not found"));

            existingComment.setText(updatedComment.getText());
            existingComment.setCreateDate(updatedComment.getCreateDate());
            existingComment.setUser(updatedComment.getUser());
            existingComment.setTask(updatedComment.getTask());

            return commentRepository.save(existingComment);
        });
    }

    @Async
    @Override
    @Transactional
    public CompletableFuture<Void> deleteComment(Long id) {
        return CompletableFuture.runAsync(() -> {
            if (!commentRepository.existsById(id)) {
                throw new RuntimeException("Comment with ID " + id + " not found");
            }
            commentRepository.deleteById(id);
        });
    }

    @Async
    @Override
    public CompletableFuture<Comment> getCommentById(Long id) {
        return CompletableFuture.supplyAsync(() -> (Comment) commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment with ID " + id + " not found")));
    }

    @Async
    @Override
    public CompletableFuture<List<javax.xml.stream.events.Comment>> getAllComments() {
        return CompletableFuture.supplyAsync(commentRepository::findAll);
    }

    @Async
    @Override
    public CompletableFuture<List<Comment>> getCommentsByTaskId(Long taskId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Comment> allComments = commentRepository.findAll();
            List<Comment> filteredComments = allComments.stream()
                    .filter(comment -> comment.getTask() != null && comment.getTask().getId().equals(taskId))
                    .collect(Collectors.toList());

            if (filteredComments.isEmpty()) {
                throw new RuntimeException("No comments found for the given task ID: " + taskId);
            }
            return filteredComments;
        });
    }

    @Async
    @Override
    public CompletableFuture<List<Comment>> getCommentsByUserId(Long userId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Comment> allComments = commentRepository.findAll();
            List<Comment> filteredComments = allComments.stream()
                    .filter(comment -> comment.getUser() != null && comment.getUser().getId().equals(userId))
                    .collect(Collectors.toList());

            if (filteredComments.isEmpty()) {
                throw new RuntimeException("No comments found for the given user ID: " + userId);
            }
            return filteredComments;
        });
    }
}