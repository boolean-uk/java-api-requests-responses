package com.booleanuk.api.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {

    private String title;
    private int numPages;
    private String author;
    private String genre;
    private int id;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }
}
