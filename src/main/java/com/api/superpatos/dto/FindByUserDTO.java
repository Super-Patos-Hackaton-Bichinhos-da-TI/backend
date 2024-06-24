package com.api.superpatos.dto;

import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;

public record FindByUserDTO(
        String email,
        Squad squad,
        Role role
) {
}
