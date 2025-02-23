package com.spring.project.organicfoodshop.domain.request.auth;

import lombok.*;

@Getter
@Setter
@Builder
public class RegisterRequest {
    private String fullName;

    private String email;

    private String phone;

    private String username;

    private String password;

    private String confirmPassword;
}
