package com.spring.project.organicfoodshop.domain.response.common.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayedCommentResponse {
    private Long id;

    @JsonProperty("is_visible")
    private Boolean isVisible;
}
