package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("GoT", 780, "George RR Martin", "Fantasy"));
        add(new Book("Hunger Games", 311, "Suzanne Collins", "Dystopian"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);

        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable(name = "id") int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(
            @PathVariable(name = "id") int id,
            @RequestBody Book newBook) {
        for (int i=0; i<books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, newBook);
                books.get(i).setId(id);
                return books.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book deleteBook(@PathVariable(name = "id") int id) {
        for (int i = 0; i < this.books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                return this.books.remove(i);
            }
        }
        return null;
    }
}
