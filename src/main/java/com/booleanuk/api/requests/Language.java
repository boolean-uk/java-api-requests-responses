package com.booleanuk.api.requests;

class Language {
    private static int LID = 0;
    private String name;

    private int ID;

    public Language() {

    }

    public Language(String name) {
        this.ID = ++LID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
