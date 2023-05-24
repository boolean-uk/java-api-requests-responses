package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {

    private List<Book> books = new ArrayList<>() {{
        add(new Book("Java for Dummies", 356, "ThanosPa", "Programming"));
        add(new Book("C# for Dummies", 653, "DTsimaras", "Programming"));
    }};

    @GetMapping
    public List<Book> getBooks() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable(name = "id") int id) {
        if (id < this.books.size())
            return this.books.get(id);

        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);

        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@RequestBody Book book, @PathVariable(name = "id") int id) {
        if (id < this.books.size()) {
            this.books.get(id).setAuthor(book.getAuthor());
            this.books.get(id).setTitle(book.getTitle());
            this.books.get(id).setNumPages(book.getNumPages());
            this.books.get(id).setGenre(book.getGenre());
            return this.books.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable(name = "id") int id) {
        if (id < this.books.size()) {
            Book tempBook = this.books.get(id);
            this.books.remove(id);
            return tempBook;
        }
        return null;
    }


}
