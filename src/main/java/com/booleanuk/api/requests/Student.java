package com.booleanuk.api.requests;

import java.util.UUID;

public class Student {
    public String firstName;
    public String lastName;

    public Student() {}

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}