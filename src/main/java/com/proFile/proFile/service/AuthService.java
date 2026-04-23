package com.proFile.proFile.service;

import com.proFile.proFile.dto.request.LoginRequest;
import com.proFile.proFile.dto.request.RegisterRequest;
import com.proFile.proFile.dto.response.AuthResponse;

public interface AuthService {
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
