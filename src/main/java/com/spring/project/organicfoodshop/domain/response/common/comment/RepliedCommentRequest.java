package com.spring.project.organicfoodshop.domain.response.common.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepliedCommentRequest {
    private Long repliedCommentId;
    private String content;

    public static class Author{
        private String fullName;
        private String avatar;
    }
}
