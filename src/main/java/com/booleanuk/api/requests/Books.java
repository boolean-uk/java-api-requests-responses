package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {

    private List<Book> books = new ArrayList<>() {{
        add(new Book(1, "Game of Thrones", 780, "George R.R. Martin", "fantasy"));
        add(new Book(2, "LeBron James", 23, "Robin K", "goat"));
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
    public Book getOne(@PathVariable(name = "id") int id) {
        for (Book b : books){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable (name = "id") int id, @RequestBody Book book) {
        for (Book b : books){
            if (b.getId() == id){
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
                b.setNumPages(book.getNumPages());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable (name = "id") int id) {
        for (Book b : books){
            if (b.getId() == id){
                books.remove(b);
                return b;
            }
        }
        return null;
    }

}




