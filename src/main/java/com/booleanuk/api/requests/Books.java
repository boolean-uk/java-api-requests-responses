package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/books")
public class Books {

    List<Book> books;

    public Books() {
        books = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        for (Book boCheck : books) {
            if (boCheck.getTitle().equals(book.getTitle())) {
                return null;
            }
        }
        Book newBook = new Book();
        newBook.setId(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setNumPages(book.getNumPages());
        newBook.setAuthor(book.getAuthor());
        newBook.setGenre(book.getGenre());
        books.add(newBook);
        return newBook;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable(name = "id") int id) {
        Book returnedBook = null;
        for (Book stu : books) {
            if (stu.getId() == id) {
                return stu;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable(name = "id") int id, @RequestBody Book book) {
        for (Book stu : books) {
            if (stu.getId() == id) {
                stu.setTitle(book.getTitle());
                stu.setNumPages(book.getNumPages());
                stu.setAuthor(book.getAuthor());
                stu.setGenre(book.getGenre());
                return stu;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable(name = "id") int id) {
        //Never delete an item directly using a loop, add it to a var instead
        //Then delete the added var later on with an if statement
        Book bookToRemove = null;

        for (Book boo : books) {
            if (boo.getId() == id) {
                bookToRemove = boo;
                break;
            }
        }

        if (bookToRemove != null) {
            books.remove(bookToRemove);
            return bookToRemove;
        } else {
            return null;
        }
    }
}
