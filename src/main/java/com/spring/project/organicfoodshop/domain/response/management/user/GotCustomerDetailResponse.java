package com.spring.project.organicfoodshop.domain.response.management.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.domain.Address;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class GotCustomerDetailResponse {
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String username;

    private String email;

    private String phone;

    private LocalDate dob;

    @JsonProperty("gender_name")
    private String genderName;

    private List<Address> addresses;

    private Boolean blocked;
}
