package com.spring.project.organicfoodshop.domain.response.common.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Builder
@Getter
@Setter
public class GotCommentsOfProduct {
    private Set<Item> items;

    @Getter
    @Setter
    public static class Item {
        private Long id;

        private String content;

        @JsonProperty("created_at")
        private Instant createdAt;

        @JsonProperty("commentator_name")
        private String commentatorName;

        @JsonProperty("is_deleted")
        private Boolean isDeleted;

        @JsonIgnore
        private Set<Comment> children;

        @JsonProperty("child_items")
        private Set<Item> childItems;
    }
}
