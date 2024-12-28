package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.management.user.CreateUserRequest;
import com.spring.project.organicfoodshop.domain.request.management.user.EditUserRequest;
import com.spring.project.organicfoodshop.domain.response.management.user.CreatedUserResponse;
import com.spring.project.organicfoodshop.domain.response.management.user.EditedUserResponse;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import com.spring.project.organicfoodshop.util.constant.GenderEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ApiRequestMessage("Call create a user API request")
    public ResponseEntity<CreatedUserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = UserMapper.INSTANCE.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.handleSaveUser(user);
        CreatedUserResponse response = UserMapper.INSTANCE.toCreatedUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{userId}")
    @ApiRequestMessage("Call edit user API request")
    public ResponseEntity<EditedUserResponse> editUser(@PathVariable Long userId, @RequestBody EditUserRequest request) {
        User user = userService.getUserByIdOrThrow(userId);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAge(request.getAge());
        user.setGender(GenderEnum.valueOf(request.getGender()));
        EditedUserResponse response = UserMapper.INSTANCE.toEditedUserResponse(user);
        return ResponseEntity.ok(response);
    }
}
