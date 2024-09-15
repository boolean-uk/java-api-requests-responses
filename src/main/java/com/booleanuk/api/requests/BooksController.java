package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final ArrayList<Book> books;
    private static int id;

    public BooksController(){
        this.books = new ArrayList<>();
        id = 1;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBook(@RequestBody Book book){
        book.setId(id++);
        this.books.add(book);
        return book;
    }

    @GetMapping
    public ArrayList<Book> getBooks(){
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id){
        for (Book book: this.books){
            if (book.getId() == id){
                return book;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book putBook(@PathVariable int id, @RequestBody Book book){
        int index = -1;
        for (int i=0; i<this.books.size(); i++){
            if (this.books.get(i).getId()==id){
                Book foundBook = this.books.get(i);
                foundBook.setTitle(book.getTitle());
                foundBook.setNumPages(book.getNumPages());
                foundBook.setAuthor(book.getAuthor());
                foundBook.setGenre(book.getGenre());
                index = i;
                break;
            }
        }
        if (index == -1){
            return null;
        } else {
            return this.books.get(index);
        }
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id){
        int index = -1;
        for (int i=0; i<this.books.size(); i++){
            if (this.books.get(i).getId()==id){
                index = i;
                break;
            }
        }
        if (index == -1){
            return null;
        } else {
            return this.books.remove(index);
        }
    }
}
