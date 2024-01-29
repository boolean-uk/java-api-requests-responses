package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>();
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        int index = 0;
        for(Book book1 : books){
           if(book1.getId() > index ){
               index = book1.getId();
           }
        }
        book.setId(index+1);
        this.books.add(book);

        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{bookId}")
    public Book getOne(@PathVariable(name = "bookId") int bookId) {
        for(Book book : books){
            if (book.getId() == bookId){
                return book;
            }
        }
        return null;
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable(name = "bookId") int bookId,@RequestBody Book book) {
        for(Book book1 : books){
            if (book1.getId() == bookId){
                book1.setAuthor(book.getAuthor());
                book1.setGenre(book.getGenre());
                book1.setTitle(book.getTitle());
                book1.setNumPages(book.getNumPages());
                return book1;
            }
        }
        return null;
    }

    @DeleteMapping("/{bookId}")
    public Book delete(@PathVariable (name = "bookId") int bookId) {
        for(Book book1 : books){
            if (book1.getId()==bookId){
                books.remove(book1);
                return book1;
            }
        }
        return null;
    }
}
