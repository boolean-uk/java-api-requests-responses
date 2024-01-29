package com.booleanuk.api.requests;

public class Book{

    private static int nextId;
    private int id;
    private String title;
    private int numPages;
    private String author;
    private String genre;

    public Book(String title, int numPages, String author, String genre) {
        nextId ++;
        this.id = nextId;
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }

    public int getId() {
        return id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}