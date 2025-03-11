package com.project.vendora.auth.service;


import com.project.vendora.auth.model.request.PasswordRequest;
import com.project.vendora.auth.model.request.SignUpRequest;
import com.project.vendora.core.entity.User;

public interface UserService {

    User saveUser(SignUpRequest signUpRequest);

    void activateUser(String email, String token, String expiresAt, PasswordRequest passwordRequest);

    User findByUsername(String username);

    void save(User user);
}
