package com.spring.project.organicfoodshop.domain.response.common.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditedAccountInfoResponse {
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    private String phone;

    private GenderEnum gender;

    private LocalDate dob;
}
