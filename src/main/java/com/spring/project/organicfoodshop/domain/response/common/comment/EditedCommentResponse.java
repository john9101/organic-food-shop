package com.spring.project.organicfoodshop.domain.response.common.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class EditedCommentResponse {
    private String content;
    private Instant editedAt;
    private Author author;

    public static class Author{
        private String fullName;
        private String avatar;
    }
}
