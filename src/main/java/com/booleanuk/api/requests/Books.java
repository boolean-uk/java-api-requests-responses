package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {

    private List<Book> books = new ArrayList<>() {
        {
            add(new Book( 0,"title", 10, "author", "genre"));
            add(new Book( 1,"title2", 10, "author2", "genre2"));
        }
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        book.setId(books.get(books.size()-1).getId()+1);
        this.books.add(book);
        return book;
    }
    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getSingle(@PathVariable Integer id){
        for (Book book : books) {
            if (book.getId()==id){
                return new ResponseEntity<>(book,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == id) {
                return new ResponseEntity<>(books.remove(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id,@RequestBody Book updatedBook){
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == id) {
                books.get(i).setAuthor(updatedBook.getAuthor());
                books.get(i).setTitle(updatedBook.getTitle());
                books.get(i).setNumPages(updatedBook.getNumPages());
                books.get(i).setGenre(updatedBook.getGenre());
                return new ResponseEntity<>(books.get(i), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }


}
