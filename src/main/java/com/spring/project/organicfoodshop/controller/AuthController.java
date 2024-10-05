package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.auth.LoginRequest;
import com.spring.project.organicfoodshop.domain.request.auth.RegisterRequest;
import com.spring.project.organicfoodshop.domain.response.auth.AuthResponse;
import com.spring.project.organicfoodshop.domain.response.auth.RegisterResponse;
import com.spring.project.organicfoodshop.service.EmailService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.RandomUtil;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final SecurityUtil securityUtil;
    private final EmailService emailService;

    @PostMapping("/register")
    @ApiRequestMessage("Call registration API request")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws MessagingException {
        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationKey(RandomUtil.generateActivationKey());
        user = userService.handleSaveUser(user);
        RegisterResponse registerResponse = userMapper.toRegisterResponse(user);
        Context context = new Context();
        context.setVariable("email", registerResponse.getEmail());
        context.setVariable("username", registerResponse.getUsername());
        emailService.sendEmail(registerResponse.getEmail(), "Email to verify account information", "verifyAccountTemplate", context);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @PostMapping("/login")
    @ApiRequestMessage("Call login API request")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String accessToken = securityUtil.createToken(authentication, false);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(authentication.getPrincipal());
        AuthResponse authResponse = AuthResponse.builder()
                .accessToken(accessToken)
                .build();
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/activate")
    @ApiRequestMessage("Call activate API request")
    private ResponseEntity<String> activate(@RequestParam String activateKey) {
        return ResponseEntity.status(HttpStatus.OK).body("Activated");
    }
}



