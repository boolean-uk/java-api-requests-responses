package com.booleanuk.api.requests;

public class Book {
    private String title, author,genre;
    private int numPages, id;

    public Book(String title, String author, String genre, int numPages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numPages = numPages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
    public Book setId(int id){
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }
}
