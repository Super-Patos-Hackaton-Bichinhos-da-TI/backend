package com.api.superpatos.enums;

import lombok.Getter;

@Getter
public enum Squad {
    FRONT_END("Front-end"),
    BACK_END("Back-end"),
    CYBER_SECURITY("CyberSecurity"),
    UX_UI("UX/UI"),
    DADOS("Dados"),
    GAMES("Games"),
    MARKETING("Marketing"),
    MOBILE("Mobile"),
    QA("QA");

    private final String squad;

    Squad(String squad) {
        this.squad = squad;
    }
}
