package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")

public class Books {
    private List<Book> books;

    Books() {
        this.books = new ArrayList<>() {{
            add(new Book("title1", 400, "Author1", "Genre1"));
            add(new Book("title2", 401, "Author2", "Genre2"));
        }};
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("{id}")
    public Book getABook(@PathVariable int id) {
        for (Book book : books) {
            if (id == book.getId()) {
                return book;
            }
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        for (int i = 0; i < books.size(); i++) {
            if(id == books.get(i).getId()) {
               books.get(i).setTitle(book.getTitle());
                books.get(i).setNumPages(book.getNumPages());
                books.get(i).setAuthor(book.getAuthor());
                books.get(i).setGenre(book.getGenre());
                return book;
            }
        }

        return null;
    }

    @DeleteMapping("{id}")
    public Book deleteBook(@PathVariable int id) {
        for (int i = 0; i < books.size(); i++) {
            if(id == books.get(i).getId()) {
                return books.remove(i);
            }
        }
        return null;
    }

}
