package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("books")
public class Books {
    private HashMap<Integer, Book> books = new HashMap<>() {
        {
            this.put(1, new Book("Book1", 20000, "Anonymous", "Life Struggles"));
            this.put(2, new Book("Harry Potter", 400, "J.K Rowling", "Fantasy"));
            this.put(3, new Book("Unicorn Sparkles and Fluffy Rainbows", 400, "Miss Sparkle", "Fantasy"));
        }
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.put(book.getId(), book);
        return book;
    }

    @GetMapping
    public HashMap<Integer, Book> getAll() {
        return this.books;
    }

    @GetMapping("{id}")
    public Book getSpecific(@PathVariable int id) {
        if (this.books.get(id) != null) {
            return this.books.get(id);
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        if (this.books.get(id) != null) {
            book.putId(id);
            this.books.put(id, book);
            return book;
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id) {
        Book book = this.books.get(id);
        if (book != null) {
            this.books.remove(id);
            return book;
        }
        return null;
    }

}
