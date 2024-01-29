package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("books")
@RestController
public class Books {

    private List<Book> books;

    public Books() {
        this.books = new ArrayList<>();
    }

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
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable(name = "id") int id) {
        return this.books.stream().filter(x->x.getId() == id).findFirst().orElseThrow(BookNotFoundException::new);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable(name = "id") int id, @RequestBody Book reqBook) {
        Book book = this.books.stream().filter(x->x.getId() == id).findFirst().orElseThrow(BookNotFoundException::new);
        book.setTitle(reqBook.getTitle());
        book.setNumPages(reqBook.getNumPages());
        book.setAuthor(reqBook.getAuthor());
        book.setGenre(reqBook.getGenre());
        return book;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable(name = "id") int id) {
        Book book = this.books.stream().filter(x->x.getId() == id).findFirst().orElseThrow(BookNotFoundException::new);
        this.books.remove(book);
        return book;
    }
}
