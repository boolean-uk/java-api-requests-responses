package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> books = new ArrayList<>() {{
        add(new Book(1, "A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book(2, "A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
    }};

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Book already exists.");
            }
        }

        book.setId(books.size() + 1);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping
    public ResponseEntity<?> getAllLanguages() {
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getABook(@PathVariable int id) {
        for (Book b: books) {
            if (b.getId() == id) {
                return ResponseEntity.ok(b);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id) {
        for (Book b: books) {
            if (b.getId() == id) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Updated Book");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable int id) {
        boolean removed = books.removeIf(
                book -> (book.getId() == id));

        if (removed) {
            return ResponseEntity.ok("Book deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }
}
