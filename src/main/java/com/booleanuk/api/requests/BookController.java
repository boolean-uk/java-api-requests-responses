package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        books.add(book);

        return book;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return books;
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable int id){
        return getBook(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook){
        Book book = getBook(id);

        if (book != null){
            int index = books.indexOf(book);

            books.get(index).setAuthor(updatedBook.getAuthor());
            books.get(index).setGenre(updatedBook.getGenre());
            books.get(index).setNumPages(updatedBook.getNumPages());
            books.get(index).setTitle(updatedBook.getTitle());

            return books.get(index);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id){
        Book book = getBook(id);

        if (book != null){
            int index = books.indexOf(book);

            return books.remove(index);
        }

        return null;
    }

    private Book getBook(int id){
        for (Book book : books){
            if (book.getID() == id){
                return book;
            }
        }

        return null;
    }
}
