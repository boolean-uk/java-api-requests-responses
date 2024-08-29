package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class Books {
    private ArrayList<Book> books = new ArrayList<>() {{
        add(new Book("A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book("The Final Empire", 541, "Brandon Sanderson", "Fantasy"));
        add(new Book("Ender's Game", 324, "Orson Scott Card", "Sci-Fi"));
    }};

    @GetMapping
    public ArrayList<Book> getBooks() {
        return books;
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable int id) {
        try {
            Book b = books.stream().filter(book -> book.getId() == id).findFirst().orElseThrow();
            return b;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book putBook(@PathVariable int id, @RequestBody Book updatedBook) {
        try {
            Book b = books.stream().filter(book -> book.getId() == id).findFirst().orElseThrow();
            b.setAuthor(updatedBook.getAuthor());
            b.setGenre(updatedBook.getGenre());
            b.setNumPages(updatedBook.getNumPages());
            b.setTitle(updatedBook.getTitle());
            return b;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    @DeleteMapping("{id}")
    public Book deleteBook(@PathVariable int id) {
        try {
            Book b = books.stream().filter(book -> book.getId() == id).findFirst().orElseThrow();
            books.remove(b);
            return b;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
