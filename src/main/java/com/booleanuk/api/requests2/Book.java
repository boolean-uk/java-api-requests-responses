package com.booleanuk.api.requests2;

public class Book {

    private static int nextId = 0;
    private String title;
    private int numPages;
    private String author;
    private String genre;
    private int id;
    public Book(String title, int numPages, String author, String genre){
        this.id = nextId++;
        this.title = setTitle(title);
        this.numPages = setNumPages(numPages);
        this.author = setAuthor(author);
        this.genre = setGenre(genre);
    }




    // getters & setters
    public int getId(){
        return this.id;
    }
    public String getTitle() {
        return title;
    }
    public String setTitle(String title) {
        return this.title = title;
    }
    public int getNumPages() {
        return numPages;
    }
    public int setNumPages(int numPages) {
        return this.numPages = numPages;
    }
    public String getAuthor() {
        return author;
    }
    public String setAuthor(String author) {
        return this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public String setGenre(String genre) {
        return this.genre = genre;

    }
}
