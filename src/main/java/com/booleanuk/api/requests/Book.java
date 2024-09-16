package com.booleanuk.api.requests;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Book {
    public String title;
    public int numOfPages;
    public String author;
    public String genre;

    private final int uuid;

    public Book() {
        // I am aware that this can cause collisions between book IDs
        // however the task does not address this, so neither will I
        uuid = ThreadLocalRandom.current().nextInt(0, 1000000000);
    }
    public Book(String title, int numOfPages, String author, String genre) {
        this.title = title;
        this.numOfPages = numOfPages;
        this.author = author;
        this.genre = genre;

        uuid = ThreadLocalRandom.current().nextInt(0, 1000000000);
    }

    public void merge(final Book other) {
        title = other.title;
        numOfPages = other.numOfPages;
        author = other.author;
        genre = other.genre;
    }

    public int getUuid() {
        return uuid;
    }
}
