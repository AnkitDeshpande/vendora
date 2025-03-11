package com.project.vendora.auth.service.impl;

import com.project.vendora.auth.model.request.ChangePasswordRequest;
import com.project.vendora.auth.model.request.LoginRequest;
import com.project.vendora.auth.model.response.LoginResponse;
import com.project.vendora.auth.service.AuthService;
import com.project.vendora.auth.service.TokenService;
import com.project.vendora.auth.service.UserService;
import com.project.vendora.core.constant.Database;
import com.project.vendora.core.constant.ErrorMessage;
import com.project.vendora.core.constant.TokenType;
import com.project.vendora.core.entity.Role;
import com.project.vendora.core.entity.Token;
import com.project.vendora.core.entity.User;
import com.project.vendora.core.exception.VendoraException;
import com.project.vendora.core.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        String generatedToken = jwtService.generateToken(loginRequest.getUsername());
        User user = userService.findByUsername(loginRequest.getUsername());
        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        // Save the token for the authenticated user
        Token token = new Token();
        token.setToken(generatedToken);
        token.setTokenType(TokenType.LOGIN.name());
        token.setUser(user);
        token.setIsDeleted(Boolean.FALSE);
        tokenService.save(token);

        return LoginResponse.builder()
                .id(user.getId())
                .username(authentication.getName())
                .token(jwtService.generateToken(loginRequest.getUsername()))
                .tenant(Objects.isNull(user.getTenant()) ? Database.DEFAULT_DATABASE.getDbName() : user.getTenant().getDbName())
                .profilePic(user.getProfilePictureUrl())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            user.setActive(Boolean.FALSE);
            user.setIsDeleted(Boolean.FALSE);
            userService.save(user);
        } else {
            throw new VendoraException(404, ErrorMessage.USER_NOT_FOUND.getMessage());
        }
    }

    @Override
    @Transactional
    public void changePassword(String username, ChangePasswordRequest changePasswordRequest) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new VendoraException(ErrorMessage.USER_NOT_FOUND.getCode(),
                    ErrorMessage.USER_NOT_FOUND.getMessage());
        }


        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new VendoraException(ErrorMessage.INCORRECT_OLD_PASSWORD.getCode(),
                    ErrorMessage.INCORRECT_OLD_PASSWORD.getMessage());
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new VendoraException(ErrorMessage.PASSWORDS_DO_NOT_MATCH.getCode(),
                    ErrorMessage.PASSWORDS_DO_NOT_MATCH.getMessage());
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userService.save(user);
    }

    @Override
    @Transactional
    public void logout(String token) {
        if (token == null || token.isEmpty()) {
            throw new VendoraException(ErrorMessage.TOKEN_REQUIRED.getCode(), ErrorMessage.TOKEN_REQUIRED.getMessage());
        }

        Token existingToken = tokenService.findByToken(token);
        if (existingToken != null) {
            existingToken.setIsDeleted(Boolean.TRUE);
            tokenService.save(existingToken);
        } else {
            throw new VendoraException(ErrorMessage.TOKEN_NOT_FOUND.getCode(), ErrorMessage.TOKEN_NOT_FOUND.getMessage());
        }
    }
}
