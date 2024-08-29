package com.booleanuk.api.requests;

public class Book {

    private String title;
    private Integer numPages;
    private String author;
    private String genre;
    public static int uniqueId = 0;
    private int id;

    public Book(String title, Integer numPages, String author, String genre) {
        this.id = uniqueId;
        uniqueId++;
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }

    public static int getUniqueId() {
        return uniqueId;
    }

    public static void setUniqueId(int uniqueId) {
        Book.uniqueId = uniqueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
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
