package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll(){
        return this.books;
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable int id){
        for(int i = 0; i < books.size(); ++i){
            if(books.get(i).getId() == id){
                return this.books.get(i);
            }
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book){
        for(int i = 0; i < books.size(); ++i){
            if(books.get(i).getId() == id){
                this.books.set(i, book);
                return this.books.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id){
        for(int i = 0; i < books.size(); ++i){
            if(books.get(i).getId() == id){
                return this.books.remove(i);
            }
        }
        return null;
    }
}
