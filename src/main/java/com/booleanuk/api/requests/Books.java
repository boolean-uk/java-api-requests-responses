package com.booleanuk.api.requests;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class Books {
    private ArrayList<Book> books = new ArrayList<>(){{
        add(new Book(
                "Dune",
                200,
                "Frank Herbert",
                "Science Fiction"
                ));
        add(new Book(
                "Kafka on the Shore",
                500,
                "Hiraku Murakami",
                "Fiction"
                ));
    }};

    @GetMapping
    public ArrayList<Book> getAll() {
        return this.books;
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable int id) {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getId() == id) {
                return this.books.get(i);
            }
        }
        return null;
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @PutMapping("{id}")
    public Book update(@PathVariable int id, @RequestBody Book book) {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getId() == id) {
                this.books.get(i).setTitle(book.getTitle());
                this.books.get(i).setNumPages(book.getNumPages());
                this.books.get(i).setAuthor(book.getAuthor());
                this.books.get(i).setGenre(book.getGenre());
                return this.books.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id) {
        for(int i = 0; i < this.books.size(); i++) {
            if(this.books.get(i).getId() == id) {
                return this.books.remove(i);
            }
        }
        return null;
    }
}
