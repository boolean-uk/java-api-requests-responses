package com.booleanuk.api.requests;

import static com.booleanuk.api.requests.CreateUniqueID.createID;

class Student {
    private String firstName;
    private String lastName;

    private final String uniqueID;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniqueID = createID();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}