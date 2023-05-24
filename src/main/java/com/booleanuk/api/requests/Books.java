package com.booleanuk.api.requests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>() {{
        add(new Book(UUID.randomUUID().toString(),"Book A",780,"A","A"));
        add(new Book(UUID.randomUUID().toString(),"Book B",780,"B","D"));
        add(new Book(UUID.randomUUID().toString(),"Book C",780,"C","C"));
    }};
    @GetMapping
    public List<Book> getAll(){
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable(name = "id") String id) {
        for (Book b : this.books) {
            if (b.getId().equals(id)){
                return b;
            }
        }

        return null;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        book.setId(UUID.randomUUID().toString());
        this.books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable (name = "id") String id, @RequestBody Book book){
        for (Book b: this.books) {
            if (b.getId().equals(id)){
                // Do some updates
                b.setTitle(book.getTitle());
                b.setNumPages(book.getNumPages());
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable( name = "id") String id) {
        for (int i =0;i< books.size();i++){
            if (books.get(i).getId().equals(id)){
                return this.books.remove(i);
            }
        }
        return null;
    }
}