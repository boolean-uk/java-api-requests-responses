package com.booleanuk.api.requests;

public class Book {
    private static int nextID;
    private int id;
    private String title;
    private int pages;
    private String author;
    private String genre;

    public Book(String title,
                int    pages,
                String author,
                String genre)
    {
        nextID+=1;
        this.id = nextID;
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.genre = genre;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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
