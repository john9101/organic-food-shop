package com.spring.project.organicfoodshop.domain.response.management.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditedUserResponse {
    private Long id;
    private String username;

    @JsonProperty("full_name")
    private String fullName;
    private String phone;
    private String email;
    private String avatar;
}
