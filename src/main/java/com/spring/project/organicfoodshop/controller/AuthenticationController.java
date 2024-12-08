package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.auth.LoginRequest;
import com.spring.project.organicfoodshop.domain.request.auth.RegisterRequest;
//import com.spring.project.organicfoodshop.domain.response.auth.InitializedPrincipalResponse;
import com.spring.project.organicfoodshop.domain.response.auth.IntrospectedResponse;
import com.spring.project.organicfoodshop.domain.response.auth.LoggedInResponse;
import com.spring.project.organicfoodshop.domain.response.auth.RegisteredResponse;
import com.spring.project.organicfoodshop.service.CartService;
import com.spring.project.organicfoodshop.service.EmailService;
import com.spring.project.organicfoodshop.service.JwtService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.event.RegisterEvent;
import com.spring.project.organicfoodshop.service.mapper.UserMapper;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ApplicationEventPublisher eventPublisher;
    private final JwtService jwtService;
    private final UserService userService;
    private final CartService cartService;
    private final EmailService emailService;

    @Value("${security.authentication.jwt.refresh-token-validity-in-seconds}")
    private Long refreshTokenValidityInSeconds;

    @PostMapping("/register")
    @ApiRequestMessage("Call register API request")
    public ResponseEntity<RegisteredResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws MessagingException {
        User user = UserMapper.INSTANCE.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        eventPublisher.publishEvent(new RegisterEvent(this, user));
        user = userService.handleSaveUser(user);
        cartService.handleSaveCart(user.getCart());
        Map<String, Object> templateParams = new HashMap<>();
        templateParams.put("phone", user.getPhone());
        templateParams.put("email", user.getEmail());
        templateParams.put("activationToken", user.getActivationToken());
        emailService.sendEmailWithTemplate(1L, templateParams, user.getEmail());
        RegisteredResponse registerResponse = UserMapper.INSTANCE.toRegisterResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @GetMapping("/activate")
    @ApiRequestMessage("Call activate API request")
    private ResponseEntity<?> activate(@RequestBody String activationToken) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @ApiRequestMessage("Call login API request")
    public ResponseEntity<LoggedInResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.getUserByEmail(SecurityUtil.extractFromPrincipal(authentication), false);
        LoggedInResponse.UserInfo userInfo = UserMapper.INSTANCE.toLoggedInUserInfo(user);
        String accessToken = jwtService.createToken(authentication, userInfo, false);
        String refreshToken = jwtService.createToken(authentication, userInfo, true);
        LoggedInResponse loginResponse = LoggedInResponse.builder()
                .accessToken(accessToken)
                .userInfo(userInfo)
                .build();
        ResponseCookie responseCookie = ResponseCookie
                .fromClientResponse("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenValidityInSeconds)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(loginResponse);
    }

    @GetMapping("/introspect")
    @ApiRequestMessage("Call introspect API request")
    public ResponseEntity<IntrospectedResponse> introspect() {
        User user = userService.getUserByEmail(SecurityUtil.getCurrentUserPrincipal().orElse(null), false);
        IntrospectedResponse.UserInfo userInfo = UserMapper.INSTANCE.toIntrospectedUserInfo(user);
        IntrospectedResponse introspectedResponse = IntrospectedResponse.builder().userInfo(userInfo).build();
        return ResponseEntity.ok().body(introspectedResponse);
    }
}



