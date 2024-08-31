package com.booleanuk.api.requests;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BooksTest {
  private Books controller;

  @BeforeEach
  public void setup() {
    this.controller = new Books();
    this.controller.create(new UnidentifiedBook("A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
  }

  @Test
  public void testGetAll() {
    List<Book> languages = this.controller.getAll();

    Assertions.assertEquals(1, languages.size());
    Assertions.assertEquals(Books.getIdCounter() - 1, languages.get(0).id());
    Assertions.assertEquals("A Game of Thrones", languages.get(0).title());
    Assertions.assertEquals(780, languages.get(0).numPages());
    Assertions.assertEquals("George R.R. Martin", languages.get(0).author());
    Assertions.assertEquals("Fantasy", languages.get(0).genre());
  }

  @Test
  public void testGetByName() {
    Optional<Book> book = this.controller.getById(Books.getIdCounter() - 1);

    Assertions.assertTrue(book.isPresent());
    Assertions.assertEquals("A Game of Thrones", book.get().title());
  }

  @Test
  public void testUpdateByName() {
    UnidentifiedBook updatedBook = new UnidentifiedBook("A Game of Thrones", 780, "John F. Kennedy", "Fantasy");
    Optional<Book> book = this.controller.updateById(Books.getIdCounter() - 1, updatedBook);

    Assertions.assertTrue(book.isPresent());
    Assertions.assertEquals("A Game of Thrones", book.get().title());
    Assertions.assertEquals("John F. Kennedy", book.get().author());
  }

  @Test
  public void testDeleteByName() {
    Optional<Book> book = this.controller.deleteById(Books.getIdCounter() - 1);

    Assertions.assertTrue(book.isPresent());
    Assertions.assertEquals("A Game of Thrones", book.get().title());

    Optional<Book> notABook = this.controller.deleteById(Books.getIdCounter() + 50);
    Assertions.assertFalse(notABook.isPresent());
  }
}
