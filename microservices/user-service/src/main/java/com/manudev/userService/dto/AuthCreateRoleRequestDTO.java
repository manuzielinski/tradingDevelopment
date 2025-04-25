package com.manudev.userService.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRoleRequestDTO(@Size(max = 3, message = "El usuario no puede tener mas de 3 Roles")List<String> roleListName) {
}
