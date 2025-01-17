package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>() {{
        add(new Book("Lord of the ring", 1000, "Tolkien", "Fantasy"));
        add(new Book("Harry Potter and the prisoner of azkaban", 650, "J.K Rowling", "Fantasy"));
    }};

    // Creating a book and adding it to the list
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    // Get all books
    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    // Get book by id
    @GetMapping("/{id}")
    public Book getSpecificBook(@PathVariable int id) {
        for (Book book : this.books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Update book by id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateSpecificBook(@PathVariable int id, @RequestBody Book book) {
        for (Book aBook : this.books) {
            if (aBook.getId() == id) {
                aBook.setTitle(book.getTitle());
                aBook.setNumPages(book.getNumPages());
                aBook.setAuthor(book.getAuthor());
                aBook.setGenre(book.getGenre());
                return aBook;
            }
        }
        return null;
    }

    // Deleting book by id
    @DeleteMapping("/{id}")
    public Book deleteSpecificBook(@PathVariable int id) {
        for (Book book : this.books) {
            if (book.getId() == id) {
                this.books.remove(book);
                return book;
            }
        }
        return null;
    }
}
