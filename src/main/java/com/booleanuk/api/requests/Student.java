package com.booleanuk.api.requests;

class Student {
    private static int SID = 0;
    private String firstName;
    private String lastName;
    private int ID;

    public Student(String firstName, String lastName) {
        this.ID = ++SID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static int getSID() {
        return SID;
    }

    public int getID() {
        return ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}