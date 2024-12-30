package com.spring.project.organicfoodshop.domain.request.auth;

import lombok.*;

@Getter
@Setter
@Builder
public class LoginRequest {
    private String email;

    private String password;
}
