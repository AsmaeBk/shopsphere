package com.asmae.shopsphere.controller;

import org.springframework.web.bind.annotation.RestController;

import com.asmae.shopsphere.model.User;
import com.asmae.shopsphere.model.AuthenticationResponse;
import com.asmae.shopsphere.model.LoginRequest;
import com.asmae.shopsphere.model.RegisterRequest;
import com.asmae.shopsphere.model.UserResponse;
import com.asmae.shopsphere.service.UserService;

import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody RegisterRequest newUSer) {
        
        return ResponseEntity.ok(userService.createUser(newUSer));
    }
    
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(userService.login(request));
    }
}
