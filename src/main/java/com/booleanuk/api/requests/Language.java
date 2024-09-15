package com.booleanuk.api.requests;

public class Language {
    private String name;

    public Language(){ this.name = "unknown"; }

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
