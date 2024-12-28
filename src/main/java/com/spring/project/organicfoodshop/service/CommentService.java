package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Comment;
import com.spring.project.organicfoodshop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment handleSaveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Set<Comment> getAllComments() {
        return Set.copyOf(commentRepository.findAll());
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
