package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("A Game of Thrones", 780,
                "George R.R Martin", "Fantasy"));}
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll(){return this.books;}

    @GetMapping("/{id}")
    public Book getSpesificBook(@PathVariable int id){
        for (Book book : books){
            if (book.getId() == id){
                int index = this.books.indexOf(book);
                return this.books.get(index);
            }
        }
        return null;
    }

}
