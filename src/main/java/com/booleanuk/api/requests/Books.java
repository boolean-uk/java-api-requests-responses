package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        for(int i = 0; i < books.size(); i++) {
            if (id == books.get(i).getId()) {
                return this.books.get(i);
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        Book updateBook = getBook(id);
        if(updateBook != null) {
            updateBook.setId(book.getId());
            updateBook.setNumPages(book.getNumPages());
            updateBook.setTitle(book.getTitle());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setGenre(book.getGenre());
            return updateBook;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        Book deleteBook = getBook(id);
        if(deleteBook != null) {
            books.remove(deleteBook);
            return deleteBook;
        }
        return null;
    }


}
