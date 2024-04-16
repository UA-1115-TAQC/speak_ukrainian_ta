package com.academy.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String roleName;
    private String urlLogo;
    private String status;

    public UserResponseDto() {
    }
}
