package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books;
    public Books() {
        this.books = new ArrayList<>();

        this.books.add(new Book("LOTR", 1200, "J.R.R Tolkien", "Fantasy"));
        this.books.add(new Book("SOIAF", 500, "G.R.R. Martin", "Fantasy"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        if(book.getBookID() == 0) {
            return null;
        }
        this.books.add(book);

        return book;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return this.books;
    }

    @GetMapping("{id}")
    public Book getOneBook(@PathVariable int id) {
        for(Book bok : books) {
            if (bok.getBookID() == id) {
                return bok;
            }
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book) {
        for(Book bok : books) {
            if(bok.getBookID() == id) {
                this.books.get(id).setTitle(book.getTitle());
                this.books.get(id).setNumPages(book.getNumPages());
                this.books.get(id).setAuthor(book.getAuthor());
                this.books.get(id).setGenre(book.getGenre());

                return bok;
            }
        }

        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id) {
        for (Book bok : books) {
            if (bok.getBookID() == id) {
                return this.books.remove(id);
            }
        }
        return null;
    }

}
