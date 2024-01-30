package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class BookController {
    private ArrayList<Book> books;
    private int id;
    public BookController(){
        this.books = new ArrayList<>();
        books.add(new Book(1,"Hassan1", 200,"Shitt","horror"));
        books.add(new Book(2,"Hassan2",300 ,"Shitt","comedy"));
        books.add(new Book(3,"Hassan3",250, "Shitt","scary"));
    }
    @GetMapping
    public ArrayList<Book> getAll(){
        return this.books;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book created(@RequestBody Book book){
        this.books.add(book);
        return book;
    }

    @GetMapping("books/{id}")
    public String getOneBook(@PathVariable int id){
        if ( id < this.books.size()){
            return this.books.get(id).title;
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book){
        if (id < this.books.size()){
            this.books.get(id).setTitle(book.getTitle());
            this.books.get(id).setNumPage(book.getNumPage());
            this.books.get(id).setAuthor(book.getAuthor());
            this.books.get(id).setGenre(book.getGenre());
            return this.books.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id){
        if (id < this.books.size()){
            return this.books.remove(id);
        }
        return null;
    }

}
