package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("books")

public class Books {
    private List<Book> books = new ArrayList<>() {{
        add(new Book("Sovjetistan", 499, "Erika Fatland", "Travel"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> create(@RequestBody Book book) {
        this.books.add(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book getBook(@PathVariable int id) {
        for (Book book1 : books) {
            if (book1.getId() == id) {
                return book1;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        for (Book book1 : this.books) {
            if (book1.getId() == id) {
                book1.setTitle(book.getTitle());
                book1.setNumPages(book.getNumPages());
                book1.setAuthor(book.getAuthor());
                book1.setGenre(book.getGenre());
                return ResponseEntity.ok(book1);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public List<Book> deleteBook(@PathVariable int id) {
        Book bookToRemove = null;
        for (Book book1 : books) {
            if (book1.getId() == id) {
                bookToRemove = book1;
                break;
            }
        }
        if (bookToRemove != null) {
            this.books.remove(bookToRemove);
            return this.books;
        } else {
            return null;
        }

    }
}
