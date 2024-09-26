package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/books")
public class BookController {

    private ArrayList<Book> books;

    public BookController(ArrayList<Book> books) {
        this.books = books;
    }

    @GetMapping
    public ArrayList<Book> getBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable int id) {
        if (id < this.books.size()) {
            return this.books.get(id);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        if (!books.contains(book)) {
            this.books.add(book);
            return book;
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        if (id < this.books.size()) {
            this.books.get(id).setTitle(book.getTitle());
            this.books.get(id).setNumPages(book.getNumPages());
            this.books.get(id).setAuthor(book.getAuthor());
            this.books.get(id).setGenre(book.getGenre());
            return book;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book delete(@PathVariable int id) {
        if (id < this.books.size()) {
            this.books.remove(id);
        }
        return null;
    }
}
