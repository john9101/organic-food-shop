package com.spring.project.organicfoodshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment extends AbstractAuditingEntity{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> children;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isVisible;

    private Boolean isDeleted;

    private String commentatorName;

    @PrePersist
    protected void onPrePersistComment() {
        this.isVisible = true;
        this.isDeleted = false;
        this.commentatorName = this.user.getFullName();
    }
}
