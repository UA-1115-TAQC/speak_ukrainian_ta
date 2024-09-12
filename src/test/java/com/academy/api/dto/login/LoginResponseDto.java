package com.academy.api.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponseDto {
    private Integer id;
    private String email;
    private String roleName;
    private String accessToken;
    private String refreshToken;
}
