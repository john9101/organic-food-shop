package com.spring.project.organicfoodshop.controller;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;

    @PatchMapping("/current")
    @ApiRequestMessage("Call edit account API request")
    public ResponseEntity<?> editAccount(){
        return ResponseEntity.ok(null);
    }
}
