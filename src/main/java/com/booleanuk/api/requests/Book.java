package com.booleanuk.api.requests;

public class Book {
    private String title;
    private int numPages;
    private String author;
    private String genre;
    private int id;
    private static int nextId = 1;

    public Book(String title, int numPages, String author, String genre) {
        this.id = nextId++;
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
