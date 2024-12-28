package com.spring.project.organicfoodshop.domain.request.common.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCommentRequest {
    private Long productId;
    private String content;
}
