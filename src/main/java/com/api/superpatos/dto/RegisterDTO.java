package com.api.superpatos.dto;

import com.api.superpatos.enums.Role;

public record RegisterDTO(
        String username,
        String password,
        Role roles
) {
}
