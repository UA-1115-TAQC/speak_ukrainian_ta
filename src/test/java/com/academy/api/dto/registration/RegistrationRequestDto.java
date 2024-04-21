package com.academy.api.dto.registration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RegistrationRequestDto(String firstName,
                                     String lastName,
                                     String email,
                                     String password,
                                     String phone,
                                     String roleName
) {

}

