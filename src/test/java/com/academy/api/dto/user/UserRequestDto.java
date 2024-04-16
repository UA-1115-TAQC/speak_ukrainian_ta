package com.academy.api.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserRequestDto(Integer id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String roleName,
        String urlLogo,
        String status
) {
}
