package com.booleanuk.api.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Language {
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public Language(){}

}
