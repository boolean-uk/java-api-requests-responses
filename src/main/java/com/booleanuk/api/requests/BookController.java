package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


//Extension Exercise
@RestController
@RequestMapping("/books")
public class BookController {

    private HashMap<Integer, Book> books;
    private int idCounter;

    public BookController() {
        this.books = new HashMap<>();
        this.idCounter = 0;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HashMap<Integer, Book> getAllBooks() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable (name = "id") int id) {
        return this.books.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        book.setId(this.idCounter);
        this.books.put(this.idCounter, book);
        this.idCounter++;
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable(name = "id") int id, @RequestBody Book book) {
        if (this.books.containsKey(id)) {
            //book.setId(id);
            this.books.put(id, book);
            return book;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book deleteBook(@PathVariable (name = "id") int id) {
        return this.books.remove(id);
    }
}
