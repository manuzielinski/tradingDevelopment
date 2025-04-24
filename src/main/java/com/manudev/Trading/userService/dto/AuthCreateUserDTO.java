package com.manudev.Trading.userService.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserDTO(@NotBlank String username, @NotBlank String password, @Valid AuthCreateRoleRequestDTO roleRequest) {
}
