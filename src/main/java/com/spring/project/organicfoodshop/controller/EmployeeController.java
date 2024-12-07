package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.management.role.AssignRoleToEmployeeRequest;
import com.spring.project.organicfoodshop.domain.response.management.role.AssignedRoleToEmployeeResponse;
import com.spring.project.organicfoodshop.service.RoleService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final RoleService roleService;
    private final UserService userService;

    @PatchMapping("/{id}/roles")
    @ApiRequestMessage("Call assign role to employee API request")
    public ResponseEntity<AssignedRoleToEmployeeResponse> assignRoleToEmployee(
            @PathVariable("id") Long id,
            @RequestBody AssignRoleToEmployeeRequest assignRoleToEmployeeRequest){
        Set<Role> roles = roleService.getAllRolesById(assignRoleToEmployeeRequest.getRoleIds());
        User employee = userService.getUserByIdOrThrow(id);
        User assignedEmployee = userService.handleAssignRoleToEmployee(employee, assignRoleToEmployeeRequest.getDelegatePassword(), roles);
        AssignedRoleToEmployeeResponse assignedRoleToEmployeeResponse = AssignedRoleToEmployeeResponse.builder()
                .employeeName(employee.getFullName())
                .assignedRoleAt(assignedEmployee.getUpdatedBy())
                .assignedRoleBy(assignedEmployee.getUpdatedBy())
                .build();
        return ResponseEntity.ok(assignedRoleToEmployeeResponse);
    }
}
