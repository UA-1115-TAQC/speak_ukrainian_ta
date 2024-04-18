package com.academy.api.registration.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RegistrationRequestDto(
        String email,
        String firstName,
        String lastName,
        String phone,
        String password,
        String roleName) {
}

