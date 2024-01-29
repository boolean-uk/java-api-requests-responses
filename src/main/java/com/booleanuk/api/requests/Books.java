package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class Books {
    private ArrayList<Book> books;
    public Books()
    {
        this.books = new ArrayList<>();
    }

    @GetMapping
    public ArrayList<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getSpecificBook(@PathVariable int id)   {
        for(Book b: this.books)
        {
            if(b.getId() == id)
                return b;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        for(Book b : this.books)
        {
            if(b.getId() == id)
            {
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
                b.setPages(book.getPages());
                b.setTitle(book.getTitle());
                return b;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        for(Book b : this.books)
        {
            if(b.getId() == id)
            {
                this.books.remove(b);
                return b;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
