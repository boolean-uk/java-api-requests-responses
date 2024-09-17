package com.booleanuk.api.requests;

import static com.booleanuk.api.requests.CreateUniqueID.createID;

public class Language {
    private String name;

    private final String uniqueID;

    public Language(String name) {
        this.name = name;
        this.uniqueID = createID();
    }

    //Needed open constructor for testing post Language on insomnia wihtout error
    public Language() {
        this.uniqueID = createID();
    }

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
