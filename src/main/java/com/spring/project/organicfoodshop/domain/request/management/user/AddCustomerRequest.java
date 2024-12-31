package com.spring.project.organicfoodshop.domain.request.management.user;

import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class AddCustomerRequest {
    private String fullName;

    private String email;

    private String phone;

    private LocalDate dob;

    private GenderEnum gender;

    private String username;

    private String password;

    private String confirmPassword;

    private Set<Address> addresses;
}
