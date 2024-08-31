package com.booleanuk.api.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class Books {
  private final List<Book> books = new ArrayList<>();
  private static int idCounter = 0;

  private static Book addId(UnidentifiedBook book) {
    return new Book(Books.idCounter++, book.title(), book.numPages(), book.author(), book.genre());
  }

  public static int getIdCounter() {
    return Books.idCounter;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book create(@RequestBody UnidentifiedBook book) {
    Book newBook = Books.addId(book);
    this.books.add(newBook);

    return newBook;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Book> getAll() {
    return this.books;
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Book> getById(@PathVariable int id) {
    return this.books.stream()
        .filter(book -> book.id() == id)
        .findFirst();
  }

  @PutMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Optional<Book> updateById(@PathVariable int id, @RequestBody UnidentifiedBook newBook) {
    return this.getById(id)
        .map(oldBook -> {
          Book newBookWithId = Books.addId(newBook);
          this.books.remove(oldBook);
          this.books.add(newBookWithId);
          return newBookWithId;
        });
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Book> deleteById(@PathVariable int id) {
    return this.getById(id)
        .map(bookToDelete -> {
          this.books.remove(bookToDelete);
          return bookToDelete;
        });
  }
}
