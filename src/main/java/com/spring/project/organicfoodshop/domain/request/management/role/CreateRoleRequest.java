package com.spring.project.organicfoodshop.domain.request.management.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CreateRoleRequest {
    @NotBlank(message = "Name of role cannot be blank")
    private String name;

    @NotBlank(message = "Password of delegate cannot be blank")
    @JsonProperty("delegate_pw")
    private String delegatePassword;

    @Nullable
    private String description;

    @Nullable
    private Set<Long> permissionIds;
}
