package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class Books {
    private List<Book> books;
    private int currentId;

    public Books(){
        this.books = new ArrayList<>();
        this.books.add(new Book("A Game of Thrones", 780, "George R.R. Martin", "Fantasy").setId(1));
        this.books.add(new Book("Cien años de Soledad", 780, "Gabriel García Marquez", "Magic Realism").setId(2));
        this.currentId = 3;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        this.books.add(book.setId(currentId));
        currentId++;
        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll(){
        return this.books;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable(name = "id") int id){
        for(Book searchbook: books){
            if(searchbook.getId()== id){
                return searchbook;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable(name = "id") int id, @RequestBody Book book){
        for(Book searchbook: books){
            if(searchbook.getId()== id){
                searchbook.setAuthor(book.getAuthor());
                searchbook.setGenre(book.getGenre());
                searchbook.setTitle(book.getTitle());
                searchbook.setNumPages(book.getNumPages());
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable(name = "id") int id){
        for(Book searchbook: books){
            if(searchbook.getId()== id){
                return this.books.remove(this.books.indexOf(searchbook));
            }
        }
        return null;
    }


}
