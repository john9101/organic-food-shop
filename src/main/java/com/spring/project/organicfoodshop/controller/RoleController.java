package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Permission;
import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.request.management.role.CreateRoleRequest;
import com.spring.project.organicfoodshop.service.PermissionService;
import com.spring.project.organicfoodshop.service.RoleService;
import com.spring.project.organicfoodshop.service.mapper.RoleMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @GetMapping
    @ApiRequestMessage("Call get all roles API request")
    public ResponseEntity<Set<Role>> getAllRoles() {
        Set<Role> allRoles = roleService.getAllRoles();
        return ResponseEntity.ok(allRoles);
    }

    @PostMapping
    @ApiRequestMessage("Call create role API request")
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest createRoleRequest) {
        Set<Permission> permissions = permissionService.getAllPermissionsById(createRoleRequest.getPermissionIds());
        Role role = RoleMapper.INSTANCE.toRole(createRoleRequest);
        if(!permissions.isEmpty()){
            role.setPermissions(permissions);
        }
        role = roleService.handleCreateRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }
}
