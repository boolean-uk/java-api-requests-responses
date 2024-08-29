package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/books")
public class Books {

    ArrayList<Book> books;

    public Books() {
        books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", 180, "F. Scott Fitzgerald", "Fiction"));
        books.add(new Book("To Kill a Mockingbird", 281, "Harper Lee", "Fiction"));
        books.add(new Book("1984", 328, "George Orwell", "Dystopian"));
        books.add(new Book("Pride and Prejudice", 279, "Jane Austen", "Romance"));
        books.add(new Book("The Catcher in the Rye", 277, "J.D. Salinger", "Fiction"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        books.add(book);
        return book;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book get(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, book);
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book remove(@PathVariable int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return books.remove(i);
            }
        }
        return null;
    }

}
