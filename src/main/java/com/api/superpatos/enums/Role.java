package com.api.superpatos.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Admin"),
    MANAGER("Manager"),
    USER("User");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}
