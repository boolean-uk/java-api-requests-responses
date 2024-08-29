package com.booleanuk.api.requests;

public class Book {

    private static int idCount = 0;
    private int id;
    private String title;
    private int numOfPages;
    private String author;
    private String genre;

    public Book(String title, int numOfPages, String author, String genre) {
        idCount++;
        this.id = idCount;
        this.title = title;
        this.numOfPages = numOfPages;
        this.author = author;
        this.genre = genre;
    }

    public void putId(int id) {
        idCount--;
        this.id = id;
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

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
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
}
