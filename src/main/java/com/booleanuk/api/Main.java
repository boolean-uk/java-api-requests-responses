package com.booleanuk.api;

import com.booleanuk.api.requests.Students;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Students s = new Students();
        s.findStudentByName("Dave");
    }
}
