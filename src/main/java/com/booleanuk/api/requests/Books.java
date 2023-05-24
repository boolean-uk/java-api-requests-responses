package com.booleanuk.api.requests;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        this.books.add(book);
        book.setId(this.books.indexOf(book) + 1);
        return book;
    }

    @GetMapping
    public List<Book> getAll(){
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable (name = "id") int id){
        for (Book b : this.books){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable (name = "id") int id, @RequestBody Book book){
        for (Book b : this.books){
            if (b.getId() == id){
                b.setTitle(book.getTitle());
                b.setNumPages(book.getNumPages());
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable (name = "id") int id){
        for (Book b : this.books){
            if (b.getId() == id){
                this.books.remove(b);
                return b;
            }
        }
        return null;
    }





}
