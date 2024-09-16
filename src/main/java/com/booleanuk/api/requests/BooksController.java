package com.booleanuk.api.requests;

import com.booleanuk.api.exceptions.BadRequestException;
import com.booleanuk.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasLength;
@RestController
@RequestMapping("/books")
public class BooksController {

    private final List<Book> books;

    public BooksController() {
        books = new ArrayList<>();
        books.add(new Book(1, "To Kill a Mockingbird", 281, "Harper Lee", "Fiction"));
        books.add(new Book(2, "1984", 328, "George Orwell", "Dystopian"));
        books.add(new Book(3, "Pride and Prejudice", 279, "Jane Austen", "Classic"));
    }

    private int idCounter = 4;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        if (!hasLength(book.getAuthor()) || !hasLength(book.getGenre()) || !hasLength(book.getTitle()) || book.getNumPages() <= 0) {
            throw new BadRequestException("Author, genre, title, and a positive number of pages are required.");
        }
        int newId = idCounter++;
        Book newBook = new Book(newId, book.getTitle(), book.getNumPages(), book.getAuthor(), book.getGenre());
        books.add(newBook);
        return newBook;
    }
    
    @GetMapping
    public List<Book> getAllBooks() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getSpecificBook(@PathVariable int id) {
        Optional<Book> foundBook = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();

        if (foundBook.isPresent()) {
            return foundBook.get();
        } else {
            throw new NotFoundException("Book not found");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        if (!hasLength(book.getAuthor()) || !hasLength(book.getGenre()) || !hasLength(book.getTitle()) || book.getNumPages() <= 0) {
            throw new BadRequestException("Author, genre, title, and a positive number of pages are required.");
        }
        Optional<Book> foundBook = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (foundBook.isPresent()) {
            Book existingBook = foundBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setNumPages(book.getNumPages());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setGenre(book.getGenre());
            return existingBook;
        } else {
            throw new NotFoundException("Book not found");
        }
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable int id) {
        Optional<Book> foundBook = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();

        if (foundBook.isPresent()) {
            Book deletedBook = foundBook.get();
            books.remove(deletedBook);
            return deletedBook;
        } else {
            throw new NotFoundException("Book not found");
        }
    }
}
