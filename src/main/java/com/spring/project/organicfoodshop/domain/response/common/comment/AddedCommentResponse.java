package com.spring.project.organicfoodshop.domain.response.common.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AddedCommentResponse {
    private Long id;

    private String content;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("commentator_name")
    private String commentatorName;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
