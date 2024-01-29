package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("Bookoe", 345, "George", "Scenery"));
        add(new Book("Ertel", 345, "Bush", "Action"));
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

    @GetMapping("/{id}")
    public Book getOne(@PathVariable int id) {
        for (Book book1 : books) {
            if (book1.getBookId() == (id)) {
                return book1;
            }

        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateId(@PathVariable int id, @RequestBody Book book) {
        for (Book book1 : books) {
            if (book1.getBookId() == (id)) {
                book1.setTitle(book.getTitle());
                book1.setNumPages(book.getNumPages());
                book1.setAuthor(book.getAuthor());
                book1.setGenre(book.getGenre());
                return book1;
            }


        }
        return null;
    }


    @DeleteMapping("/{id}")
    public Book deleteId(@PathVariable int id) {
        for (Book book1 : books) {
            if (book1.getBookId() == (id)) {
                this.books.remove(book1);
                return book1;
            }

        }
        return null;
    }
}
