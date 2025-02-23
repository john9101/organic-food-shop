package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findAllByProductIdAndParentIsNull(Long productId);
}
