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

    public void setFirstName(String newName) {this.firstName = newName;}

    public void setLastName(String newName) {this.lastName = newName;}

}