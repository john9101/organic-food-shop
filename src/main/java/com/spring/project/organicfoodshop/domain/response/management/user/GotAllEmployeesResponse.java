package com.spring.project.organicfoodshop.domain.response.management.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GotAllEmployeesResponse {
    private List<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long id;

        @JsonProperty("full_name")
        private String fullName;

        private String username;

        private String email;

        private String phone;

        private String dob;

        private Boolean blocked;
    }
}
