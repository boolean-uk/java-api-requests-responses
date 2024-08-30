package com.booleanuk.api.requests;

import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
class Student {

    @Setter
    private String firstName;
    @Setter
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
}