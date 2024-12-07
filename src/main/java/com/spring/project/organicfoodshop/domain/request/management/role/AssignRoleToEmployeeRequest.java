package com.spring.project.organicfoodshop.domain.request.management.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class AssignRoleToEmployeeRequest {
    private Set<Long> roleIds;

    @NotBlank(message = "Password of delegate cannot be blank")
    @JsonProperty("delegate_pw")
    private String delegatePassword;
}
