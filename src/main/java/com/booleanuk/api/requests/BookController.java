package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books = new ArrayList<>() {{
        add(new Book("Rock", 360, "Steven", "Simulation"));
    }};


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        int check = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() > check){
                check = books.get(i).getId();
            }
        }
        book.setId(check+1);
        this.books.add(book);

        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable(name = "id") int id) {
        for (int i = 0; i < books.size(); i++) {
            if (this.books.get(i).getId() == id) { // 10 < 5
                return this.books.get(id);
            }
        }

        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable(name = "id") int id, @RequestBody Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                this.books.get(id).setTitle(book.getTitle());
                this.books.get(id).setNumPages(book.getNumPages());
                this.books.get(id).setAuthor(book.getAuthor());
                this.books.get(id).setGenre(book.getAuthor());

                return this.books.get(id);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable(name = "id") int id) {
        for (int i = 0; i < books.size(); i++) {
            if (this.books.get(i).getId() == id) {
                return this.books.remove(id);
            }
        }
        return null;
    }
}

