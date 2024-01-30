package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("books")
public class BookController {
    private HashMap<Integer, Book> books;
    private int idIterator = 83638;

    public BookController() {
        books = new HashMap<>();
    }

    @GetMapping
    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        idIterator++;
        this.books.put(idIterator, new Book(idIterator, book.getTitle(), book.getNumPages(), book.getAuthor(), book.getGenre()));
        return this.books.get(idIterator);
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book getSpecificBook(@PathVariable Integer id) {
        if (books.containsKey(id)) {
            return books.get(id);
        }
        return null;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updatBook(@PathVariable Integer id, @RequestBody Book book) {
        if (books.containsKey(id)) {
            this.books.put(id, new Book(id, book.getTitle(), book.getNumPages(), book.getAuthor(), book.getGenre()));
            return this.books.get(id);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public HashMap<Integer, Book> deleteBook(@PathVariable Integer id) {
        this.books.remove(id);
        return this.books;
    }
}
