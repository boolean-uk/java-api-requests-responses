package com.booleanuk.api.requests;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>() {{
        add(new Book("bible", 1500, "God", "adventure"));
        add(new Book("introduction to programming", 100, "frida", "mystery"));
        add(new Book("harry potter", 700, "jk rowling", "fantasy"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return this.getOne(book.getId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll() {return this.books;}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable int id) {
        for (Book book : this.books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@PathVariable int id, @RequestBody Book book) {
        Book bookToUpdate = this.getOne(id);
        if (bookToUpdate != null) {
            this.books.get(id).setTitle(book.getTitle());
            this.books.get(id).setNumPages(book.getNumPages());
            this.books.get(id).setAuthor(book.getAuthor());
            this.books.get(id).setGenre(book.getGenre());
            return this.books.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable int id) {
        Book book = this.getOne(id);
        if (book != null) {
            this.books.remove(id);
        }
        return book;
    }
}
