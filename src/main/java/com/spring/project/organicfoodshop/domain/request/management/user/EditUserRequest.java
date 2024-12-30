package com.spring.project.organicfoodshop.domain.request.management.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EditUserRequest {
    private String fullName;

    private String email;

    private String phone;

    private Integer age;

    private String gender;

    private String avatar;
}
