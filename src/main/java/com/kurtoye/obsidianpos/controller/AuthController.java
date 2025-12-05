package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.exceptions.UserException;
import com.kurtoye.obsidianpos.payload.dto.UserDTO;
import com.kurtoye.obsidianpos.payload.response.AuthResponse;
import com.kurtoye.obsidianpos.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // http://localhost:4200/auth/register


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerHandler(
            @RequestBody UserDTO userDTO
    ) {
        try {
            return new ResponseEntity<>(
                    authService.register(userDTO),
                    HttpStatus.CREATED
            );
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDTO userDTO
    ) {
        try {
            return new ResponseEntity<>(
                    authService.login(userDTO),
                    HttpStatus.CREATED
            );
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
    }

}
