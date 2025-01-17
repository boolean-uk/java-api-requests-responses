package com.booleanuk.api.requests;

public class Book {
    private int id;
    private static int nextId;
    private String title;
    private String author;
    private int numPages;
    private String genre;

    public Book(String title, String author, int numPages, String genre) {
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.genre = genre;
        nextId++;
        this.id = nextId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
