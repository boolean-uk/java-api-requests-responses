package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("A Game of Thrones", 780,
                "George R.R Martin", "Fantasy"));}
    };


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll(){return this.books;}

    @GetMapping("/{id}")
    public Book getSpesificBook(@PathVariable int id){
        for (Book book : books){
            if (book.getId() == id){
                int index = this.books.indexOf(book);
                return this.books.get(index);
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book book ){
        for (Book curBook : books){
            if (curBook.getId() == id){
                int index = this.books.indexOf(curBook);
                this.books.get(index).setTitle(book.getTitle());
                this.books.get(index).setAuthor(book.getAuthor());
                this.books.get(index).setNumPages(book.getNumPages());
                this.books.get(index).setGenre(book.getGenre());
                return this.books.get(index);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id){
        for (Book book : books){
            if (book.getId() == id){
                int index = this.books.indexOf(book);
                return this.books.remove(index);
            }
        }
        return null;
    }
}
