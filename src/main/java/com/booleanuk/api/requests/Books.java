package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("books")
public class Books {

    private int id = 1;
    private ArrayList<Book> books = new ArrayList<>(){{
        add(new Book(0,"A Game of Thrones",780,"George R.R. Martin","Fantasy"));
    }};;

    @GetMapping
    public ArrayList<Book> getAll() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable (name = "id") int id) {
        for (Book book : books) {
            if (book.getId() == id ) {
                return book;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        book.setId(this.id);
        this.id++;
        this.books.add(book);
        return this.books.get(this.books.indexOf(book));
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable (name = "id") int id) {
        for (Book book : books) {
            if (book.getId() == id ) {
                this.books.remove(book);
                return book;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable (name = "id") int id, @RequestBody Book book) {
        for (Book indexBook : books) {
            if (indexBook.getId() == id ) {
                int index = this.books.indexOf(indexBook);
                this.books.get(index).setTitle(book.getTitle());
                this.books.get(index).setAuthor(book.getAuthor());
                this.books.get(index).setNumPages(book.getNumPages());
                this.books.get(index).setGenre(book.getGenre());
                return this.books.get(index);
            }
        }
        return null;
    }
}
