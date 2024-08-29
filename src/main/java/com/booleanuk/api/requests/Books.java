package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("books")
public class Books {
    private HashMap<String, Book> books;
    private Random r = new Random();
    private String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public Books() {
        books = new HashMap<>();
        /* {{
            put(generateId(), new Book("Game of Thrones", 700, "George", "Fantasy"));
            put(generateId(), new Book("Invisible Women", 356, "Dr. Lisa", "Science"));
        }}*/
    }

    private String generateId() {
        int length = 4;
        StringBuilder newId = new StringBuilder();
        for (int i = 0; i < length; i++){
            int randomIndex = this.r.nextInt(this.alphaNumeric.length());
            newId.append(this.alphaNumeric.charAt(randomIndex));
        }
        return newId.toString();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        if(book.getTitle().isEmpty()) {
            return null;
        }

        String id = generateId();
        book.setId(id);
        this.books.put(book.getId(), book);

        return book;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HashMap<String, com.booleanuk.api.requests.Book> getBooks() {
        return books;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book getBookWithId(@PathVariable String id) {
        return books.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book deleteBookWithId(@PathVariable String id) {
        Book deleteBook = this.books.get(id);
        this.books.remove(id);
        return deleteBook;
    }

    @PutExchange("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable String id, @RequestBody Book book){
        book.setId(id);
        this.books.put(id, book);

        return this.books.get(id);
    }

}
