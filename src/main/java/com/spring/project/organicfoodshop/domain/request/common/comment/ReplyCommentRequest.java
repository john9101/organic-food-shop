package com.spring.project.organicfoodshop.domain.request.common.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCommentRequest {
    private Long id;
    private String content;
}
