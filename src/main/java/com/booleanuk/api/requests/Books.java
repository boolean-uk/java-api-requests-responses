package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {

    private List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // CREATED = 201 code
    public Book create(@RequestBody Book book) {
        book.setId(Book.idManager);
        ++Book.idManager;
        this.books.add(book);
        return book;
    }

    @GetMapping("{id}")
    public Book get(@PathVariable int id) {
        for (Book b : this.books) if (b.getId() == id) return b;
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED) // CREATED = 201 status code
    public Book update(@PathVariable int id, @RequestBody Book updatedBook) {
        for (int i = 0; i < this.books.size(); ++i) {
            Book currentBook = this.books.get(i);
            if (currentBook.getId() == id) {
                currentBook.setTitle(updatedBook.getTitle());
                currentBook.setNumPages(updatedBook.getNumPages());
                currentBook.setAuthor(updatedBook.getAuthor());
                currentBook.setGenre(updatedBook.getGenre());
                return currentBook;
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id) {
        for (int i = 0; i < this.books.size(); ++i) {
            if (this.books.get(i).getId() == id) return this.books.remove(i);
        }
        return null;
    }

}
