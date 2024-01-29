package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Book;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> bookList;

    public BookController() {
        this.bookList = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        book.setId(UUID.randomUUID());
        this.bookList.add(book);
        log.info("Added: " + book);
        return book;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable UUID id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody Book newBook) {
        for (Book book : this.bookList) {
            if (book.getId().equals(id)) {
                log.info("Updating: [" + id + "] with new values " + newBook);
                // Set all values except UUID to new values
                book.setAuthor(newBook.getAuthor());
                book.setTitle(newBook.getTitle());
                book.setGenre(newBook.getGenre());
                book.setNumPages(newBook.getNumPages());
                return ResponseEntity.status(HttpStatus.CREATED).body(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable UUID id) {
        for (int i = 0; i < this.bookList.size(); i++) {
            if (this.bookList.get(i).getId().equals(id)) {
                log.info("Removing: [" + id + "]");
                return ResponseEntity.ok(this.bookList.remove(i));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
