package com.spring.project.organicfoodshop.domain.response.common.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DeletedCommentResponse {
    private Long id;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
