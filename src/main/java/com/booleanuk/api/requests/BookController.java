package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    public final List<Book> books = new ArrayList<>() {{
        add(new Book("Nineteen Eighty-Four", 328, "George Orwell", "Dystopian, Political Fiction"));
    }};

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping("/{uuid}")
    public Book get(@PathVariable int uuid) {
        final int _index = findBook(uuid);
        return _index >= 0 ? books.get(_index) : null;
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book update(@PathVariable int uuid, @RequestBody Book book) {
        final int _index = findBook(uuid);
        if (_index < 0) return null;

        books.get(_index).merge(book);

        return books.get(_index);
    }

    @DeleteMapping("/{uuid}")
    public Book remove(@PathVariable int uuid) {
        final int _index = findBook(uuid);
        return _index >= 0 ? books.remove(_index) : null;
    }

    private int findBook(final int uuid) {
        for (int i = 0; i < books.size(); i++)
            if (books.get(i).getUuid() == uuid)
                return i;
        return -1;
    }
}
