package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>() {{
        add(new Book("Game of thrones", 100, "Dont know", "ksf"));
        add(new Book("Sdflsøm", 100, "aslf", "horro"));
        add(new Book("Søm", 100, "aslf", "horro"));
        add(new Book("wkefm", 40, "alf", "horror"));

    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        this.books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getSpecificBook(@PathVariable int id) {
        List<Book> allbooks = getAll();
        for (Book book: allbooks) {
            if (book.getId()==id) {
                return book;
            }
        }
        return null;

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book updateBook(@PathVariable int id, @RequestBody Book updateBook) {
        List<Book> allBooks = getAll();

        for (Book book : allBooks) {
            if (book.getId() == id) {
                book.setAuthor(updateBook.getAuthor());
                book.setGenre(updateBook.getGenre());
                book.setTitle(updateBook.getTitle());
                book.setNumPages(updateBook.getNumPages());
                return book;
            }
        }

        return null;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Book deleteBook(@PathVariable int id) {
        List<Book> allBooks = getAll();
        allBooks.removeIf(book -> book.getId() == id);
        return null;
    }

}