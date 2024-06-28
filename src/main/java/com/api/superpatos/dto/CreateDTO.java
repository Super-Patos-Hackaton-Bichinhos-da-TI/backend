package com.api.superpatos.dto;

import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;

public record CreateDTO(
        String email,
        String password,
        Squad squad,
        String office,
        Role roles
) {
}
