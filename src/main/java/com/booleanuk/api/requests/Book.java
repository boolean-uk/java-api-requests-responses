package com.booleanuk.api.requests;

public class Book {
    private String title;
    private int numPages;
    private String author;
    private String genre;
    public long id;

    public Book(String title, int numPages, String author, String genre) {
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
        // The way the ID is computed makes it so that two identical books also have the same ID,
        // and changing one parameter with PUT in one instance also changes it for the other instance.
        // I think this actually makes more sense, because if we today found out that Harry Potter actually was written
        // by a different person, this would be true for all instances of that book that exist in the world.
        this.id = (long) this.title.hashCode() * this.numPages * this.author.hashCode() * this.genre.hashCode();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void recomputeID() {
        this.id = (long) this.title.hashCode() * this.numPages * this.author.hashCode() * this.genre.hashCode();
    }
}
