package com.spring.project.organicfoodshop.domain.request.management.user;

import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class EditCustomerRequest {
    private String fullName;

    private String email;

    private String phone;

    private GenderEnum gender;

    private LocalDate dob;

    private Set<Address> addresses;
}
