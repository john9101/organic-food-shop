package com.spring.project.organicfoodshop.domain.response.management.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EditedCustomerResponse {
    private Long id;

    private String username;

    @JsonProperty("full_name")
    private String fullName;

    private String phone;

    private String email;

    private LocalDate dob;

    private List<Address> addresses;

    private boolean blocked;
}
