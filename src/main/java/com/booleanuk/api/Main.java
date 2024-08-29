package com.booleanuk.api;

import com.booleanuk.api.requests.Languages;
import com.booleanuk.api.requests.Students;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Languages languages = new Languages();
        Students students = new Students();
    }
}
