package com.booleanuk.api.requests2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BooksController {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book("No Longer Human", 176, "Osamu Dazai", "Fiction"));
        add(new Book("Harry Potter and the Chamber of Secrets", 341, "J.K. Rowling", "Fantasy"));
    }};

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
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
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        for (Book b : this.books) {
            if (b.getId() == id) {
                b.setTitle(book.getTitle());
                b.setNumPages(book.getNumPages());
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book deleteStudent(@PathVariable int id) {
        for (Book b : this.books) {
            if (b.getId() == id) {
                books.remove(b);
                return b;
            }
        }
        return null;
    }
}


