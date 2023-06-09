package com.cssp.choperiaselfservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiretoriaExecutiva {

    LAYLA("laylamscouto@gmail.com"),
    LEO("leonardo7pacheco@gmail.com");

    private final String email;

    public static String[] findEmails() {
        return new String[]{
                LAYLA.getEmail(), LEO.getEmail()
        };
    }
}
