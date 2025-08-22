package com.booleanuk.api.requests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    List<Book> books = new ArrayList<>() {{
        add(new Book (1, "A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book(2, "Harry Potter and the Philosopher's Stone", 223, "J.K. Rowling", "Fantasy"));
    }};

    private int nextId = 1;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        book.setId(nextId++);
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getABook(@PathVariable int id) {
        for (int i = 0; i<this.books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                return this.books.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book newBook) {
        for (int i = 0; i<this.books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                this.books.get(i).setTitle(newBook.getTitle());
                this.books.get(i).setNumPages(newBook.getNumPages());
                this.books.get(i).setAuthor(newBook.getAuthor());
                this.books.get(i).setGenre(newBook.getGenre());
                return this.books.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable int id) {
        for (int i = 0; i < books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                return this.books.remove(i);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book is missing");
    }


}
