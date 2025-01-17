package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable long id) throws ResponseStatusException{
        Book toReturn;
        for (Book book : this.books) {
            if (book.getId() == id) {
                toReturn = book;
                return toReturn;
            }
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no such book in the book list");

    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Book putBook(@PathVariable long id, @RequestBody Book book) throws ResponseStatusException {
        Book toPut = null;
        for (Book writing : this.books) {
            if (writing.getId() == id) {
                writing.setTitle(book.getTitle());
                writing.setNumPages(book.getNumPages());
                writing.setAuthor(book.getAuthor());
                writing.setGenre(book.getGenre());
                //A book with a changed parameter is a new book technically speaking
                writing.recomputeID();
                toPut = writing;
                return toPut;
            }
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The book does not exist in the book list");
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable long id) throws ResponseStatusException{
        Book toDelete;
        for (Book book : this.books) {
            if (book.getId() == id) {
                toDelete = book;
                books.remove(book);
                return toDelete;
            }
        } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The book does not exist in the book list");

    }

}