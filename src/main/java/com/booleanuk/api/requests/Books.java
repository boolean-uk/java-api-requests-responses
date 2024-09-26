package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("SW1", 100, "George Lucas", "Fantasy"));
        add(new Book("SW2", 200, "George Lucas", "Fantasy"));
        add(new Book("SW3", 300, "George Lucas", "Fantasy"));
        add(new Book("SW4", 400, "George Lucas", "Fantasy"));
        add(new Book("SW5", 500, "George Lucas", "Fantasy"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable int id) {
        Book localBook = null;
        for(Book book : this.books) {
            if (book.getID() == id) {
                localBook = book;
                this.books.remove(book);
                break;
            }
        }
        return localBook;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookByBID(@PathVariable int id) {
        return this.books.stream()
                .filter(book -> book.getID() == id)
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book b) {
        Book localBook = this.books.stream()
                .filter(book -> book.getID() == id)
                .findFirst()
                .orElse(null);
        if (localBook != null) {
            localBook.setTitle(b.getTitle());
            localBook.setNumPages(b.getNumPages());
            localBook.setAuthor(b.getAuthor());
            localBook.setGenre(b.getGenre());
            return localBook;
        }
        return null;
    }
}
