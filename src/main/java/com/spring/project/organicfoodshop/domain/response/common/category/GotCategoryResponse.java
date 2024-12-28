package com.spring.project.organicfoodshop.domain.response.common.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class GotCategoryResponse {
    private String name;

    private String slug;

    private Set<child> children;

    @Getter
    @Setter
    @Builder
    public static class child {
        private String name;
        private String slug;
    }
}
