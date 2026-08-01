package com.asmae.shopsphere.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asmae.shopsphere.exception.UserAlreadyExistsException;
import com.asmae.shopsphere.model.User;
import com.asmae.shopsphere.model.User.Role;
import com.asmae.shopsphere.model.AuthenticationResponse;
import com.asmae.shopsphere.model.LoginRequest;
import com.asmae.shopsphere.model.RegisterRequest;
import com.asmae.shopsphere.model.UserResponse;
import com.asmae.shopsphere.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder ;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService ;
    public UserResponse createUser(RegisterRequest newUser) {

        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new UserAlreadyExistsException(newUser.getUsername());
        }

        
        User user = User.builder()
        .username(newUser.getUsername())
        .email(newUser.getEmail())
        .password(encoder.encode(newUser.getPassword()))
        .role(Role.USER)
        .build();


        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .role(user.getRole().name())
                .email(user.getEmail())
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
    
}
