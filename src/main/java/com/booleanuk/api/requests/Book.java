package com.booleanuk.api.requests;

public class Book {

    private String title;
    private int numPages;
    private String author;
    private String genre;

    private int bookID;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
        this.bookID = 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPages() {
        return this.numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getBookID() {
        return this.bookID;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
