package com.booleanuk.api.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
@RequestMapping("/books")
public class BooksController {


    private ArrayList<Book> books;
    private int id;
    public BooksController(){
        this.books = new ArrayList<>();
        this.id = 1;
    }

    @GetMapping
    public ArrayList<Book> getAllBooks(){
        return this.books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        Book temp = new Book(book.getTitle(), book.getNumPages(), book.getAuthor(), book.getGenre(),
                id++);
        this.books.add(temp);
        return temp;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        if (updatedBook.getTitle() == null || updatedBook.getAuthor() == null || updatedBook.getNumPages() == 0 || updatedBook.getGenre() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .map(book -> {
                    book.setAuthor(updatedBook.getAuthor());
                    book.setGenre(updatedBook.getGenre());
                    book.setNumPages(updatedBook.getNumPages());
                    book.setTitle(updatedBook.getTitle());
                    return book;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                return book;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
