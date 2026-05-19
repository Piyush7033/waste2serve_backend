package com.fooddonation.system.controller;

import com.fooddonation.system.dto.LoginRequestDto;
import com.fooddonation.system.dto.RegisterRequestDto;

import com.fooddonation.system.entity.Role;

import com.fooddonation.system.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = {
                "http://localhost:3000",
                "http://localhost:5173"
        }
)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestDto dto
    ) {

        try {

            if (dto.getRole() == Role.ADMIN) {

                return ResponseEntity
                        .badRequest()
                        .body(
                                "Admin registration is not allowed"
                        );
            }

            Object response =
                    authService.register(dto);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto dto
    ) {

        try {

            Object response =
                    authService.login(dto);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return ResponseEntity.ok(
                "Auth API Working Successfully"
        );
    }
}