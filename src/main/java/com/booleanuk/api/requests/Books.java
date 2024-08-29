package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private int uniqueIdTracker = 0;
    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable int id) {
        for (Book book : this.books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        book.setId(this.uniqueIdTracker);
        this.uniqueIdTracker++;
        this.books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book newBook) {
        for (Book book : this.books) {
            if (id == book.getId()) {
                book.setTitle(newBook.getTitle());
                book.setNumPages(newBook.getNumPages());
                book.setAuthor(newBook.getAuthor());
                book.setGenre(newBook.getGenre());
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        for (Book book : this.books) {
            if (id == book.getId()) {
                this.books.remove(book);
                return book;
            }
        }
        return null;

    }
}
