package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>() {{
       add(new Book("The Big Book on Bagels", 123, "Bob", "Non-fiction"));
       add(new Book("The Mystery Bagel", 789, "Robert", "Thriller"));
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
    public Book getOne(@PathVariable (name = "id") int id) {
        for(Book book: books) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        for(Book currentBook: books) {
            if(currentBook.getId() == id) {
                currentBook.setTitle(book.getTitle());
                currentBook.setNumPages(book.getNumPages());
                currentBook.setAuthor(book.getAuthor());
                currentBook.setGenre(book.getGenre());
                return currentBook;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        for(int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id) {
                return books.remove(i);
            }
        }
        return null;
    }

}
