package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class Books {
    private ArrayList<Book> books;

    public Books() {
        this.books = new ArrayList<>();

        this.books.add(new Book(0, "The Hobbit", 300, "JRR Tolkien", "Fantasy"));
        this.books.add(new Book(1, "Pride & Prejudice", 254, "Jane Austen", "Drama"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if (book.getTitle() != null && book.getNumPages() > 0 && book.getAuthor() != null && book.getGenre() != null) {
            this.books.add(book);
            book.setId(books.indexOf(book));
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping
    public List<Book> getAll() {
        this.books.forEach(x -> x.setId(this.books.indexOf(x)));
        return books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getOne(@PathVariable(name = "id") int id) {
        if (id < this.books.size()) {
            this.books.get(id).setId(id);
            return ResponseEntity.ok().body(this.books.get(id));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> update(@PathVariable (name = "id") int id, @RequestBody Book newBook) {
        if (id < this.books.size()) {
            if (newBook.getTitle() != null && newBook.getNumPages() > 0 && newBook.getAuthor() != null && newBook.getGenre() != null) {
                Book book = this.books.get(id);
                book.setId(id);
                book.setTitle(newBook.getTitle());
                book.setNumPages(newBook.getNumPages());
                book.setAuthor(newBook.getAuthor());
                book.setGenre(newBook.getGenre());
                return ResponseEntity.ok().body(this.books.get(id));
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable (name = "id") int id) {
        if (id < this.books.size()) {
            return ResponseEntity.ok().body(this.books.remove(id));
        }
        return ResponseEntity.badRequest().body(null);
    }
}
