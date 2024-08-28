package com.booleanuk.api.requests;

public class Book extends BookRequest{
    private final int id;
    private static int nextID = 1;

    public Book(BookRequest bookRequest){
        super(bookRequest.getTitle(), bookRequest.getNumPages(), bookRequest.getAuthor(), bookRequest.getGenre());
        this.id = nextID++;
    }

    public int getID(){
        return this.id;
    }
}
