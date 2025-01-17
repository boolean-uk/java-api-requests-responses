package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("Java for dummies", 1500, "Wilmer", "Sci-fi"));
        add(new Book("C# for dummies", 500, "Einstein", "Documentary"));
    }};

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

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id){
        for(Book book : books){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book book){
        for(Book b : books){
            if(b.getId() == id){
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
                b.setTitle(book.getTitle());
                b.setNumPages(book.getNumPages());
                return b;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id){
        for(Book book : books){
            if(book.getId() == id){
                this.books.remove(book);
                return book;
            }
        }
        return null;
    }
}
