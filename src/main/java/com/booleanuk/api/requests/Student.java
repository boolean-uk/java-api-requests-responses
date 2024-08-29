package com.booleanuk.api.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}