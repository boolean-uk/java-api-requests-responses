package com.booleanuk.api.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}