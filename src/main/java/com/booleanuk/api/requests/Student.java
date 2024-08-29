package com.booleanuk.api.requests;

class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String updatedName){
        firstName = updatedName;
    }

    public void setLastName(String updatedName){
        lastName =updatedName;
    }

}