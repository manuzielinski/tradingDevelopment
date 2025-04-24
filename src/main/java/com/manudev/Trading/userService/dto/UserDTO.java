package com.manudev.Trading.userService.dto;

import com.manudev.Trading.userService.domain.USER_ROLE;
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

