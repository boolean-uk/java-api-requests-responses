package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        Book newBook = new Book(book.getTitle(), book.getNumPages(), book.getAuthor(), book.getGenre(), book.getID());
        books.add(newBook);
        return newBook;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getSpecificBook(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getID() == id)
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book existingBook = books.stream()
                .filter(book -> book.getID() == id)
                .findFirst()
                .orElse(null);

        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setNumPages(updatedBook.getNumPages());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setGenre(updatedBook.getGenre());
        }

        return existingBook;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book deleteBook(@PathVariable int id) {
        Book bookToDelete = books.stream()
                .filter(book -> book.getID() == id)
                .findFirst()
                .orElse(null);

        if (bookToDelete != null) {
            books.remove(bookToDelete);
        }

        return bookToDelete;
    }

}
