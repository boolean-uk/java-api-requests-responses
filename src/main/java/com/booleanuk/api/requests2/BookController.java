package com.booleanuk.api.requests2;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("Catching Fire", 391, "Suzanne Collins", "Dystopian"));
    }};


    @GetMapping
    public List<Book> getAllBooks(){
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable(name = "id") int id){
        for (Book book : this.books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        this.books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable(name = "id")int id, @RequestBody Book book){
        for(Book b : this.books){
            if(b.getId() == id){
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
    public Book delete(@PathVariable(name = "id") int id){
        for(Book book : this.books){
            if(book.getId() == id){
                this.books.remove(book);
                return book;
            }
        }
        return null;
    }

}
