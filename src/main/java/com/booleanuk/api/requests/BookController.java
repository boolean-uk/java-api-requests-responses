package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final ArrayList<Book> bookList;

    public BookController(){
        bookList = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book add(@RequestBody BookRequest bookRequest){
        Book book = new Book(bookRequest);
        bookList.add(book);
        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll(){
        return this.bookList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book get(@PathVariable int id){
        return bookList.stream().filter(b -> b.getID() == id).findFirst().orElseThrow();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody BookRequest bookRequest){
        if (getBookIfExist(id).isPresent()){
            Book book = bookList.get(id-1);

            book.setTitle(bookRequest.getTitle());
            book.setNumPages(bookRequest.getNumPages());
            book.setAuthor(bookRequest.getAuthor());
            book.setGenre(bookRequest.getGenre());
            return book;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Optional<Book> getBookIfExist(int id){
        return bookList.stream().filter(b->b.getID() == id).findFirst();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable int id){
        if (getBookIfExist(id).isPresent()){
            Book book = bookList.get(id-1);
            bookList.remove(book);
            return book;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
