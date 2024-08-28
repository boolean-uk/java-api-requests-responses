package com.booleanuk.api.requests;

public class BookRequest {
    private String title;
    private int numPages;
    private String author;
    private String genre;

    public BookRequest(String bookTitle, int numberOfPages, String author, String genre){
        this.title = bookTitle;
        this.numPages = numberOfPages;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle(){
        return this.title;
    }

    public int getNumPages(){
        return this.numPages;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getGenre(){
        return this.genre;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setNumPages(int newNumPages){
        this.numPages = newNumPages;
    }

    public void setAuthor(String newAuthor){
        this.author = newAuthor;
    }

    public void setGenre(String newGenre){
        this.genre = newGenre;
    }
}
