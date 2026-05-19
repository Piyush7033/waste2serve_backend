package com.fooddonation.system.service;

import com.fooddonation.system.dto.LoginRequestDto;
import com.fooddonation.system.dto.RegisterRequestDto;

import java.util.Map;

public interface AuthService {

    // ==========================================
    // REGISTER USER
    // ==========================================
    Map<String, Object> register(
            RegisterRequestDto dto
    );

    // ==========================================
    // LOGIN USER
    // ==========================================
    Map<String, Object> login(
            LoginRequestDto dto
    );
}