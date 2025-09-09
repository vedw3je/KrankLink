package dev.ved.kranklink.user_service.dto;


import dev.ved.kranklink.user_service.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private Role role;
    private String token;
    private long expiresIn;
}

