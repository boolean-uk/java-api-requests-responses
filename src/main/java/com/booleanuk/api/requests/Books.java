package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("books")
public class Books {

private ArrayList<Book> books;

public Books() {
    this.books = new ArrayList<>() {{
        add(new Book("A Song About Ice and Fire", 1234, "George R. Martin", "Fantasy"));
        add(new Book("LOTR", 432, "JRR Tolkien", "Biography"));
    }};
}
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getOne(@PathVariable (name = "id") int id) {
        for(Book book : books) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable(name = "id") int id, @RequestBody Book updatedBook) {
        for(Book book : books) {
            if(book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setNumPages(updatedBook.getNumPages());
                book.setAuthor(updatedBook.getAuthor());
                book.setGenre(updatedBook.getGenre());
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book delete(@PathVariable (name = "id") int id) {
        for(Book book : books) {
            if(book.getId() == id) {
                books.remove(book);
                return book;
            }
        }
        return null;
    }


}
