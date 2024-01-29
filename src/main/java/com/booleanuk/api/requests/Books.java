package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books;
    private static int nextId = 0;

    public Books(){
        this.books = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        book.setId(nextId++);
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll(){return this.books;}

    @GetMapping("/{id}")
    public Book getSpecificBook(@PathVariable ( name = "id") int id){
        for (Book b : this.books){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable (name = "id") int id, @RequestBody Book book){

        for (Book b : books){
            int index = this.books.indexOf(b);
            this.books.get(index).setTitle(b.getTitle());
            this.books.get(index).setAuthor(b.getAuthor());
            this.books.get(index).setNumPages(b.getNumPages());
            this.books.get(index).setGenre(b.getGenre());
            return this.books.set(index, book);


        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable (name = "id") int id){
        for ( Book b : books){
            int index = this.books.indexOf(b);
            return this.books.remove(index);
        }
        return null;
    }


}
