package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("The God Delusion", 200, "Richard Dawkins", "Philosophy"));
        add(new Book("Atlas Shrugged", 400, "Ayn Rand", "Economics"));
    }};

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return this.books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book){
        this.books.add(book);
        return book;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable int id){
        return findBookById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int id, @RequestBody Book book){
        for(Book b: books){
            if(b.getId() == id){
                b.setTitle(book.getTitle());
                b.setNumPages(book.getNumPages());
                b.setAuthor(book.getAuthor());
                b.setGenre(book.getGenre());
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id){
        for(Book b: books) {
            if (b.getId() == id) {
                books.remove(b);
                return b;
            }
        }
        return null;
    }

    private Book findBookById(int id){
        for(Book b: books){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }
}


