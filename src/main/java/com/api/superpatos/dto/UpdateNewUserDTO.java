package com.api.superpatos.dto;

import com.api.superpatos.enums.Pet;

public record UpdateNewUserDTO(
        String username,
        String email,
        String password,
        String biography,
        Pet pet
) {
}
