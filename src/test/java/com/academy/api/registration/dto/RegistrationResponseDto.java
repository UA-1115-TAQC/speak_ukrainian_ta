package com.academy.api.registration.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegistrationResponseDto {
    private Integer id;
    private String email;
    private String roleName;
}

