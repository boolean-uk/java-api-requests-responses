package com.booleanuk.api.requests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class Books {
    List<Book> books = new ArrayList<>(){{
            add(new Book("Victoria", 384, "Knut Hamsun", "Romance"));
            add(new Book("Richest boy in the world", 100, "that author", "Comedy"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book updated) {
        Book book = getBook(id);
        if (book == null) {
            return null;
        } else {
            book.setTitle(updated.getTitle());
            book.setNumPages(updated.getNumPages());
            book.setAuthor(updated.getAuthor());
            book.setGenre(updated.getGenre());
        }
        return book;
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id) {
        Book book = getBook(id);
        if (books.remove(book)) {
            return book;
        } else {
            return null;
        }
    }
}
