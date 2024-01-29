package com.booleanuk.api.requests;

public class Language {
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public Language() {
        this.name = "";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
