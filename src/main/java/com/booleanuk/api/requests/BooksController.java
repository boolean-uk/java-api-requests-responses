package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book("Bible", 1000, "Unknown", "Sci-fi"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getBooks() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getSpecificBook(@PathVariable(name = "id") int id) {
        return findBook(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for(Book book : this.books) {
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
    public Book deleteBook(@PathVariable int id) {
        Book bookToDelete = findBook(id);

        if(bookToDelete != null) {
            this.books.remove(bookToDelete);
        }

        return bookToDelete;
    }

    private Book findBook(int id) {
        return this.books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
