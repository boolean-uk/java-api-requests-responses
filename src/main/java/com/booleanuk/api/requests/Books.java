package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("books")
public class Books {
    private ArrayList<Book> books;
    private int currentId;
    public Books() {
        this.books = new ArrayList<>();
        this.books.add(new Book("The hobit","J.R.R Tolkien","Fantasy",310).setId(1));
        this.books.add(new Book("The da Vinci Code", "Dan Brown","Mystery",689).setId(2));
        this.currentId =3;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book add(@RequestBody Book book){
        this.books.add(book.setId(currentId));
        currentId++;
        return book;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getAll(){
        return this.books;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable (name = "id") int id){
        for(Book book: this.books){
            if(book.getId()==id){
                return book;
            }
        }
        return null;
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@RequestBody Book book, @PathVariable (name = "id") int id){
        for(Book temp:this.books){
            if (temp.getId()==id){
                temp.setAuthor(book.getAuthor());
                temp.setGenre(book.getGenre());
                temp.setTitle(book.getTitle());
                temp.setNumPages(book.getNumPages());
                return temp;
            }
        }
        return null;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable (name = "id") int id){
        for(Book book: this.books){
            if(book.getId()==id){
                return this.books.remove(this.books.indexOf(book));
            }
        }
        return null;
    }

}
