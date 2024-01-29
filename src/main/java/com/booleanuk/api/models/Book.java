package com.booleanuk.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Book {
    private UUID id;
    private String title;
    private int numPages;
    private String author;
    private String genre;
}
