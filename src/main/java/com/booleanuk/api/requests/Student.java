package com.booleanuk.api.requests;

public class Student {
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

//public class Student {
//
//    //    private static int nextID = 0;
////    private int id;
//    private String firstName;
//    private String lastName;
//    public Student(String firstName, String lastName) {
//
////        this.id = nextID;
//        this.firstName = firstName;
//        this.lastName = lastName;
////        nextID += 1;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
////    public int getId() {
////        return id;
////    }
//
//
//}
