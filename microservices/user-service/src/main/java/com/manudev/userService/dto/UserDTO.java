package com.manudev.userService.dto;

import com.manudev.userService.domain.USER_ROLE;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @Id
    private Long userID;
    private String username;
    private String password;
    private String email;
    private USER_ROLE userRole;
}

