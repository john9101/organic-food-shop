package com.spring.project.organicfoodshop.domain.response.management.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class GotAllCustomersResponse {
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

        private LocalDate dob;

        @JsonProperty("is_activated")
        private Boolean isActivated;

        @JsonProperty("is_blocked")
        private Boolean isBlocked;
    }
}
