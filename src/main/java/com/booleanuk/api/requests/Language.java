package com.booleanuk.api.requests;

public class Language {
    private String name;

    public Language(String name) {
        this.name = name;
    }

    public Language(){

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
