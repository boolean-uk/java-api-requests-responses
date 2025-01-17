package com.booleanuk.api.requests;

public class Book {
    private String title;
    private int numPages;
    private String author;
    private String genre;
    private static int idCounter = 0;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public int getId() {
        return id;
    }

    public Book(String title, int numPages, String author, String genre){
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
        id = idCounter++;
    }

    public Book(){
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
        id = idCounter++;
    }

}
