package com.spring.project.organicfoodshop.domain.request.management.user;

import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AddEmployeeRequest {
    private String fullName;

    private String email;

    private String phone;

    private Integer age;

    private GenderEnum gender;

    private String username;

    private String password;

    private LocalDate dob;

    private LocalDate hireDate;

    private Double salary;
}
