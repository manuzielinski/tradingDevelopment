package com.manudev.Trading.commons.dto;

import com.manudev.Trading.userService.domain.USER_ROLE;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SharedUserDTO {

    @Id
    private Long userID;
    private String username;
    private String password;
    private String email;
    private USER_ROLE userRole;
}
