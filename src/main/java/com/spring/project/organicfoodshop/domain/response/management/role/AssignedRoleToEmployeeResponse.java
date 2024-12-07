package com.spring.project.organicfoodshop.domain.response.management.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Builder
public class AssignedRoleToEmployeeResponse {

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("role_names")
    private Set<String> roleNames;

    @JsonProperty("assigned_role_at")
    private String assignedRoleAt;

    @JsonProperty("assigned_role_by")
    private String assignedRoleBy;


}
