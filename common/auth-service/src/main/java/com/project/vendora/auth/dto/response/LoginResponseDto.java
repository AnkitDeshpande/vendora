package com.project.vendora.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {
    private Long id;
    private String username;
    private String tenant;
    private Set<String> roles;
    private String profilePic;
    private String email;
    private String token;
}