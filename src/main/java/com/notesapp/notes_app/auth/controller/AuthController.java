package com.notesapp.notes_app.auth.controller;

import com.notesapp.notes_app.auth.dto.AuthResponse;
import com.notesapp.notes_app.auth.dto.LoginRequest;
import com.notesapp.notes_app.auth.dto.RegisterRequest;
import com.notesapp.notes_app.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(

            @Valid
            @RequestBody
            RegisterRequest request

    ) {

        return authService.register(
                request
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
         return authService.login(request);

    }
}
