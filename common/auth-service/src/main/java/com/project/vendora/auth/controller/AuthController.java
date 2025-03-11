package com.project.vendora.auth.controller;

import com.project.vendora.auth.dto.request.ChangePasswordRequestDto;
import com.project.vendora.auth.dto.request.LoginRequestDto;
import com.project.vendora.auth.dto.request.PasswordRequestDto;
import com.project.vendora.auth.dto.request.SignUpRequestDto;
import com.project.vendora.auth.dto.response.LoginResponseDto;
import com.project.vendora.auth.dto.response.SignUpResponseDto;
import com.project.vendora.auth.model.request.ChangePasswordRequest;
import com.project.vendora.auth.model.request.LoginRequest;
import com.project.vendora.auth.model.request.PasswordRequest;
import com.project.vendora.auth.model.request.SignUpRequest;
import com.project.vendora.auth.model.response.LoginResponse;
import com.project.vendora.auth.service.AuthService;
import com.project.vendora.auth.service.UserService;
import com.project.vendora.core.constant.SuccessMessage;
import com.project.vendora.core.entity.User;
import com.project.vendora.core.utility.Mapper;
import com.project.vendora.core.utility.MessageUtils;
import com.project.vendora.core.utility.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final Mapper mapper;

    private final MessageUtils messageUtils;

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDTO) {
        LoginResponse loginResponse = authService.login(mapper.convert(loginRequestDTO, LoginRequest.class));
        LoginResponseDto loginResponseDto = mapper.convert(loginResponse, LoginResponseDto.class);

        /* Return the ResponseEntity */
        return SuccessResponse.<LoginResponseDto>builder()
                .data(loginResponseDto)
                .statusCode(SuccessMessage.LOGIN_SUCCESS.getCode())
                .message(messageUtils.getMessage(SuccessMessage.LOGIN_SUCCESS.getMessage(), null))
                .build().getResponseEntity();
    }

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<SignUpResponseDto>> register(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.saveUser(mapper.convert(signUpRequestDto, SignUpRequest.class));
        SignUpResponseDto dtoTOSend = mapper.convert(user, SignUpResponseDto.class);
        /* Return the ResponseEntity */
        return SuccessResponse.<SignUpResponseDto>builder()
                .data(dtoTOSend)
                .statusCode(SuccessMessage.SIGN_UP_SUCCESS.getCode())
                .message(messageUtils.getMessage(SuccessMessage.SIGN_UP_SUCCESS.getMessage(), null))
                .build().getResponseEntity();
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse<String>> logout() {

        return SuccessResponse.<String>builder()
                .data(null)
                .statusCode(SuccessMessage.LOGOUT_SUCCESS.getCode())
                .message(messageUtils.getMessage(SuccessMessage.LOGOUT_SUCCESS.getMessage(), null))
                .build().getResponseEntity();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<SuccessResponse<String>> resetPassword(@RequestBody ChangePasswordRequestDto passwordResetDto) {
        authService.changePassword(passwordResetDto.getUsername(), mapper.convert(passwordResetDto, ChangePasswordRequest.class));
        return SuccessResponse.<String>builder()
                .data(null)
                .statusCode(SuccessMessage.RESET_PASSWORD.getCode())
                .message(messageUtils.getMessage(SuccessMessage.RESET_PASSWORD.getMessage(), null))
                .build().getResponseEntity();
    }

    @PostMapping("/activate")
    public ResponseEntity<SuccessResponse<String>> activateUser(@RequestParam("email") String email, @RequestParam("token") String token,
                                                                @RequestParam("expiresAt") String expiresAt, @RequestBody PasswordRequestDto passwordRequestDto) {
        userService.activateUser(email, token, expiresAt, mapper.convert(passwordRequestDto, PasswordRequest.class));
        return SuccessResponse.<String>builder()
                .data(null)
                .statusCode(SuccessMessage.USER_ACTIVATED.getCode())
                .message(messageUtils.getMessage(SuccessMessage.USER_ACTIVATED.getMessage(), null))
                .build().getResponseEntity();
    }
}
