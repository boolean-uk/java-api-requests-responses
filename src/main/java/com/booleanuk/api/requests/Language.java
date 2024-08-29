package com.booleanuk.api.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Language {
    private String name;

    public Language() {}

    public Language(String name) {
        this.name = name;
    }

}
