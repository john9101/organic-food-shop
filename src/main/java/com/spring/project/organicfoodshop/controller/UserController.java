package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Role;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.management.user.CreateUserRequest;
import com.spring.project.organicfoodshop.service.RoleService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ApiRequestMessage("Call create a user API request")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = UserMapper.INSTANCE.toUser(createUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = roleService.getAllRolesById(createUserRequest.getRoleIds());
        user.getRoles().addAll(roles);
        user = userService.handleSaveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/{userId}")
    @ApiRequestMessage("Call update user API request")
    public ResponseEntity<?> updateUserInfo(@PathVariable Long userId){
        User user = userService.getUserByIdOrThrow(userId);
        return ResponseEntity.ok(null);
    }
}
