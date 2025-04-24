package com.manudev.Trading.userService.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDTO(String message, String username, String jwt, Boolean status) {}
