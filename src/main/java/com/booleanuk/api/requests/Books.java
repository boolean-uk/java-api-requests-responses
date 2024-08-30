package com.booleanuk.api.requests;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("Life of Nathan", 265, "Nathan", "Biography"));
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

    @GetMapping("{id}")
    public Book getOne(@PathVariable int id){
        for (Book book : this.books){
            if (book.getId() == (id)){
                return book;
            }
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book){
        for (int i = 0; i < this.books.size(); i++){
            if (this.books.get(i).getId() == id){

                this.books.get(i).setTitle(book.getTitle());
                this.books.get(i).setNumPages(book.getNumPages());
                this.books.get(i).setAuthor(book.getAuthor());
                this.books.get(i).setGenre(book.getGenre());

                return this.books.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId()== id){
                return books.remove(i);
            }
        }

        return null;
    }
}
