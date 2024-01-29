package com.booleanuk.api.requests;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Language {
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
