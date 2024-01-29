package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books;

    public Books() {
        this.books = new ArrayList<>();
    }

    @GetMapping
    public List<Book> getAll(){
        return this.books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book getAll(@RequestBody Book book){
        this.books.add(book);
        return book;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable int id){
        for (Book book : books){
            if(book.getId() == id){
                return book;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id: " + id + " not found");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book book(@PathVariable int id ,@RequestBody Book book){
        for (Book bookToChange : books){
            if(bookToChange.getId() == id){
                bookToChange.setTitle(book.getTitle());
                bookToChange.setNumPages(book.getNumPages());
                bookToChange.setAuthor(book.getAuthor());
                bookToChange.setGenre(book.getGenre());
                return bookToChange;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id: " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public Book book(@PathVariable int id){
        for (Book bookToRemove : books){
            if(bookToRemove.getId() == id){
                books.remove(bookToRemove);
                return bookToRemove;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book with id: " + id + " not found");
    }
}
