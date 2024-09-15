package com.academy.api.dto.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    public int id;
    public String firstName;
    public String lastName;
    public String phone;
    public String email;
    public String urlLogo;
    public String status;
    public String roleName;
    public String password;

}
