package com.spring.project.organicfoodshop.domain.request.common.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserInfoRequest {

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is invalid")
    private String email;

    private String phone;

    private Integer age;

    private String gender;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    private String avatar;
}
