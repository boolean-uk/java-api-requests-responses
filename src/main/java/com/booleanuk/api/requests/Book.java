package com.booleanuk.api.requests;

public class Book {
    private static int BID = 0;

    private String title;
    private int numPages;
    private String author;
    private String genre;
    private int ID;

    public Book(String title, int numPages, String author, String genre) {
        this.ID = ++BID;
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }

    public static int getBID() {
        return BID;
    }

    public static void setBID(int BID) {
        Book.BID = BID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
