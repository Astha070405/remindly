package com.notesapp.notes_app.auth.service;

import com.notesapp.notes_app.auth.dto.AuthResponse;
import com.notesapp.notes_app.auth.dto.LoginRequest;
import com.notesapp.notes_app.auth.dto.RegisterRequest;
import com.notesapp.notes_app.common.exception.EmailAlreadyExistsException;
import com.notesapp.notes_app.security.jwt.JwtService;
import com.notesapp.notes_app.users.entity.User;
import com.notesapp.notes_app.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(
                    "Email already exists"
            );
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                      new RuntimeException("Invalid credentials")
                );
        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!passwordMatches){
            throw new RuntimeException(
                    "Invalid credentials"
            );
        }
        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }

}
