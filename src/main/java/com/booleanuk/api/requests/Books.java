package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class Books {

    private List<Book> books = new ArrayList<>(){{
        add(new Book(1, "A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book(1, "A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
    }};

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        this.books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(b);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookId(@PathVariable int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                b.setId(b.getId() + 1000);
                return ResponseEntity.status(HttpStatus.CREATED).body(b);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                books.remove(b);
                return ResponseEntity.status(HttpStatus.CREATED).body("Book deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
