package com.booleanuk.api.extension;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/books")
public class BooksController {
    private ArrayList<Book> books = new ArrayList<>(){{
        add(new Book("Lord Of The Rings",1000,"John Ronald","Fantasy"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addNew(@RequestBody Book book){
        this.books.add(book);
        return this.books.get(this.books.indexOf(book));
    }

    @GetMapping
    public ArrayList<Book> getAll(){
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable (name = "id") int id){
        if (id <= this.books.size()){
            return this.books.get(id);
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable (name = "id") int id, @RequestBody Book book){
        if (id <= this.books.size()){
            this.books.remove(id);
            this.books.add(book);
            return this.books.get(this.books.indexOf(book));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable (name = "id") int id){
        if (id < this.books.size()){
            this.books.remove(id);
        }
        return null;
    }

}
