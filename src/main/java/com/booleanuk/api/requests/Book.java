package com.booleanuk.api.requests;

public class Book {

    private final int id;
    private static int counter;
    private String title;
    private int numPages;
    private String author;
    private String genre;

    public Book (int id, String title, int numPages, String author, String genre){
        this.id = counter;
        counter++;
        this.title = title;
        this.numPages = numPages;
        this.author = author;
        this.genre = genre;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public int getNumPages(){
        return this.numPages;
    }

    public void setNumPages(int newNumPages){
        this.numPages = newNumPages;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String newAuthor){
        this.author = newAuthor;
    }

    public String getGenre(){
        return this.genre;
    }

    public void setGenre(String newGenre){
        this.genre = newGenre;
    }
}
