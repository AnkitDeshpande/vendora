package com.project.vendora.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDto {
    private Long id;

    private String username;

    private String email;

    private String oldPassword;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must be 8-20 characters long, contain at least one digit, one uppercase letter, one lowercase letter, one special character (@#$%^&+=), and have no spaces.")
    private String newPassword;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must be 8-20 characters long, contain at least one digit, one uppercase letter, one lowercase letter, one special character (@#$%^&+=), and have no spaces.")
    private String confirmPassword;
}
