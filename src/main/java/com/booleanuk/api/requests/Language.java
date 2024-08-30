package com.booleanuk.api.requests;

import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
public class Language {

    @Setter
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
