package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.Comment;
import com.spring.project.organicfoodshop.repository.CommentRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment handleSaveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.COMMENT)));
    }

    public Set<Comment> getCommentsByProductId(Long productId) {
        return commentRepository.findAllByProductIdAndParentIsNull(productId);
    }
}
