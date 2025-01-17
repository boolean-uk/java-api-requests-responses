package com.booleanuk.api.requests;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class BookController {
    private ArrayList<Book> books;
    private HttpServletResponse response;

    public BookController() {
        this.books = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) throws ResponseStatusException {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(book.getTitle()) && books.get(i).getAuthor().equals(book.getAuthor())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "That book is already created");
            }
        }
        books.add(book);
        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getAll() throws ResponseStatusException {
        if (books.size() > 0) {
            return books;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No books in stock!");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable int id) throws ResponseStatusException {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find the book you where searching for");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) throws ResponseStatusException {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.get(i).setTitle(book.getTitle());
                books.get(i).setGenre(book.getGenre());
                books.get(i).setAuthor(book.getAuthor());
                books.get(i).setNumPages(book.getNumPages());
                return books.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find the book you where searching for");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable int id) throws ResponseStatusException {
        Book deletedBook;
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                deletedBook = books.get(i);
                books.remove(i);
                return deletedBook;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Could not find the book you where searching for");
    }
}
