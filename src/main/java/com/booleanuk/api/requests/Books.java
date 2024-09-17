package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {

    List<Book> books = new ArrayList<>();


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookByFirstName(@PathVariable (name = "id") int id) {
        for(Book book : this.books) {
            if(book.getId() == id) {
                return book;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found by provided id");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Book updateBookByFirstName(@PathVariable (name = "id") int id, @RequestBody Book book) {
        for(Book bookEntity : this.books) {
            if(bookEntity.getId() == id) {
                bookEntity.setAuthor(book.getAuthor());
                bookEntity.setGenre(book.getGenre());
                bookEntity.setTitle(book.getTitle());
                bookEntity.setNumPages(book.getNumPages());
                return bookEntity;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found by provided id");

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Book deleteStudentByFirstName(@PathVariable(name = "id") int id) {

        for(Book book : this.books) {
            if(book.getId() == id) {
                this.books.remove(book);
                return book;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found by provided id");

    }

}
