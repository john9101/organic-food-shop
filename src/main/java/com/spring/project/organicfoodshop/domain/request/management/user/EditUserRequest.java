package com.spring.project.organicfoodshop.domain.request.management.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EditUserRequest {
    @NotBlank(message = "Họ và tên không được bỏ trống")
    private String fullName;

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    private String phone;

    private Integer age;

    private String gender;

    private String avatar;
}
