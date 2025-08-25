package com.booleanuk.api.controller;

import com.booleanuk.api.model.Book;
import com.booleanuk.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return book;
    }

    @GetMapping
    public List<Book> getBooks() {
    return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getSpecificBook(@PathVariable int id) {
        Book foundBook = bookService.findBookById(id);
        return foundBook != null ?
                ResponseEntity.ok(foundBook):
            ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {

            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable int id) {
        Book deletedBook = bookService.deleteBook(id);
        if (deletedBook != null) {
            return ResponseEntity.ok(deletedBook);

        } return ResponseEntity.notFound().build();
    }


    }


