package com.booleanuk.api.requests;

public class Book {
    private String title;
    private int numPages;
    private String author;
    private String genre;
    private static int idInc = 0;
    private int id;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numPages = numPages;
        this.id = idInc++;
    }

    public Book() {
        this.id = idInc++;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
}
