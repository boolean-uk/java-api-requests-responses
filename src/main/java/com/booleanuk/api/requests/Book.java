package com.booleanuk.api.requests;

class Book {
    private static int nextId = 0;

    private String title;
    private int numPages;
    private String author;
    private String genre;
    private int id;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
        this.id = nextId++;
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}