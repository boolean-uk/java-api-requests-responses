package com.booleanuk.api.requests;

public class Book {
    private String id;
    private String title;
    private int numPages;
    private String author;
    private String genre;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumPages() {
        return numPages;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }
}
