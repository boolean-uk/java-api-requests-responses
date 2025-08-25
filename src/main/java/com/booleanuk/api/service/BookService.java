package com.booleanuk.api.service;

import com.booleanuk.api.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>(){{
        add(new Book(1, "A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book(2, "The Fellowship of the Ring", 423, "J.R.R. Tolkien", "Fantasy"));
        add(new Book(3, "To Kill a Mockingbird", 324, "Harper Lee", "Fiction"));
        add(new Book(4, "1984", 328, "George Orwell", "Dystopian Fiction"));
        add(new Book(5, "The Catcher in the Rye", 277, "J.D. Salinger", "Fiction"));

    }};
    private Set<Integer> availableIds = new HashSet<Integer>();

    public Book createBook(Book book) {
        if (!availableIds.isEmpty()) {
            int id = availableIds.iterator().next();
            book.setId(id);
            availableIds.remove(id);
        } else if (books.isEmpty()) {
            book.setId(1);
        } else
            book.setId((books.getLast().getId()+1));
        this.books.add(book);

        return book;
    }

    public Book getSpecificBook(int id) {
        Book book = findBookById(id);
        return book;
    }
    public Book findBookById(int id) {
        Book foundBook = this.books.stream().filter(book -> book.getId()==id)
                .findFirst()
                .orElse(null);
        return foundBook;
    }

    public Book updateBook(int id,Book book) {
        Book foundBook = findBookById(id);
        if (foundBook != null) {
            foundBook.setAuthor(book.getAuthor());
            foundBook.setGenre(book.getGenre());
            foundBook.setTitle(book.getTitle());
            foundBook.setNumPages(book.getNumPages());
            return foundBook;
        }
        return null;
    }

    public Book deleteBook(int id) {
        Book foundBook = findBookById(id);
        if (foundBook != null) {
            this.books.remove(foundBook);
            availableIds.add(foundBook.getId());
            return foundBook;

        } return null;
    }
    public List<Book> getBooks() {
        return books;
    }

}
