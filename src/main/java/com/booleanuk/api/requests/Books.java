package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("{books}")
public class Books {
    private List<Book> books = new ArrayList<>() {{
        add(new Book(0,"A Game of Thrones",780,"George R.R. Martin","Fantasy"));
    }};
    private int id = 1;

    @GetMapping
    public List<Book> getAll(){
        return this.books;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable(name = "{id}") int id) {
        for (Book b : this.books) {
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

// TODO: Farhang, you have to look at the code from this part:
    // PostMapping is not complete,

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book create(@RequestBody Book book) {
//        this.books.add(book);
//        this.id++;
//        return book;
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book update(@PathVariable(name = "{id}") int id, @RequestBody Book book){
//
//    }


}