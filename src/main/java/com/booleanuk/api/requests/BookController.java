package com.booleanuk.api.requests;

import com.booleanuk.api.requests.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books;

    private static int bookID = 0;

    public BookController() {
        this.books = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        book.setBookID(bookID);
        this.books.add(book);
        bookID++;
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("{id}")
    public Book getSpecificBook(@PathVariable(name = "id") int id) {
        for (Book bookObject : this.books) {
            if (bookObject.getBookID() == id) {
                return bookObject;
            }
        }
        return null;
    }

    @PutMapping("{id}")
    public Book updateBook(@PathVariable(name = "id") int id, @RequestBody Book book) {
        for (Book bookObject : this.books) {
            if (bookObject.getBookID() == id) {
                bookObject.setTitle(book.getTitle());
                bookObject.setNumPages(book.getNumPages());
                bookObject.setAuthor(book.getAuthor());
                bookObject.setGenre(book.getGenre());
                return bookObject;
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book deleteBook(@PathVariable(name = "id") int id) {
        for (Book bookObject : this.books) {
            if (bookObject.getBookID() == id) {
                this.books.remove(bookObject);
                return bookObject;
            }
        }
        return null;
    }
}
