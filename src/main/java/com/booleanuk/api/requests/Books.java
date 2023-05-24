package com.booleanuk.api.requests;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class Books {

    private List<Book> books = new ArrayList<>() {{
        add(new Book("Java for Dummies", 356, "ThanosPa", "Programming"));
        add(new Book("C# for Dummies", 653, "DTsimaras", "Programming"));
    }};

    @GetMapping
    public List<Book> getBooks() {
        return this.books;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable (name="id") int id) {
        if (id < this.books.size())
            return this.books.get(id);

        return null;
    }
}
