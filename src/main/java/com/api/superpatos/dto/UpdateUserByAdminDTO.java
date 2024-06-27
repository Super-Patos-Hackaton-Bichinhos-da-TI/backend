package com.api.superpatos.dto;

import com.api.superpatos.enums.Pet;
import com.api.superpatos.enums.Role;
import com.api.superpatos.enums.Squad;

public record UpdateUserByAdminDTO(
        String username,
        String email,
        String password,
        String biography,
        String office,
        Squad squad,
        Pet pet,
        Role role
) {
}
