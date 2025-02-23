package com.spring.project.organicfoodshop.domain.response.management.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.EmploymentStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddedEmployeeResponse {
    private Long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    private String phone;

    private String email;

    private LocalDate dob;

    @JsonProperty("is_blocked")
    private Boolean isBlocked;

    @JsonProperty("hire_date")
    private LocalDate hireDate;

    private Double salary;

    @JsonProperty("employment_status")
    private EmploymentStatusEnum employmentStatus;
}
