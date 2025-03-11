package com.project.vendora.auth.service;

import com.project.vendora.auth.model.request.ChangePasswordRequest;
import com.project.vendora.auth.model.request.LoginRequest;
import com.project.vendora.auth.model.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    void deleteUser(String username);

    void changePassword(String username, ChangePasswordRequest changePasswordRequest);

    void logout(String token);
}

