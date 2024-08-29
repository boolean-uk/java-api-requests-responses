package com.booleanuk.api.api;


import com.booleanuk.api.Response;
import com.booleanuk.api.requests.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {
    private List<Book> books = new ArrayList<>(){{
        add(new Book("The Great Gatsby", 180, "F. Scott Fitzgerald", "Classic"));
        add(new Book("To Kill a Mockingbird", 281, "Harper Lee", "Historical Fiction"));
        add(new Book("1984", 328, "George Orwell", "Dystopian"));
        add(new Book("Moby Dick", 635, "Herman Melville", "Adventure"));
        add(new Book("Pride and Prejudice", 432, "Jane Austen", "Romance"));
        add(new Book("The Catcher in the Rye", 277, "J.D. Salinger", "Fiction"));
        add(new Book("The Hobbit", 310, "J.R.R. Tolkien", "Fantasy"));
        add(new Book("War and Peace", 1225, "Leo Tolstoy", "Historical Fiction"));
        add(new Book("Brave New World", 268, "Aldous Huxley", "Science Fiction"));
        add(new Book("The Odyssey", 541, "Homer", "Epic Poetry"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        this.books.add(new Book(book.getTitle(), book.getNumPages(), book.getAuthor(), book.getGenre()));
        return book;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAll() {
        return this.books;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getBookById(@PathVariable int id) {
        Book book = null;
        for (Book item : this.books) {
            if (item.getId() == id) {
                book = item;
                break;
            }
        }
        return book != null ? book : Response.notFound("No book found for id: " + id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Object updateBookById(@PathVariable int id, @RequestBody Book book) {

        boolean exists = this.books.stream().anyMatch(b -> b.getId() == id);
        if (!exists) {
            return Response.notFound("No book found for id: " + id);
        }

        for (Book item : this.books) {
            if (item.getId() == id) {
                item.setTitle(book.getTitle());
                item.setAuthor(book.getAuthor());
                item.setGenre(book.getGenre());
                item.setNumPages(book.getNumPages());
            }
        }
        return book;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteBookById(@PathVariable int id) {
        boolean exists = this.books.stream().anyMatch(b -> b.getId() == id);
        if (!exists) {
            return Response.notFound("No book found for id: " + id);
        }

        Book book = null;
        for (Book item : this.books) {
            if (item.getId() == id) {
                book = item;
                this.books.remove(item);
                break;
            }
        }
        return book;
    }
}
