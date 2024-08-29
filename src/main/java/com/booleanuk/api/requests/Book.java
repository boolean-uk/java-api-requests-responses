package com.booleanuk.api.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    public static int idCounter = 0;
    private int id;
    String title;
    int numPages;
    String author;
    String genre;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
        this.id = idCounter++;
    }
}
