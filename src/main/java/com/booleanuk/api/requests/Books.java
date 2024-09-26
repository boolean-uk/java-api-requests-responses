package com.booleanuk.api.requests;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class Books {

    private HashMap<Integer, Book> books;
    private Integer id;

    public Books() {
        this.books = new HashMap<>();
        id = 1;

        books.put(1, new Book("A Game of THrones", 780, "George R.R Martin", "Fantasy"));
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> create(@RequestBody Book book) {
        id += 1;
        books.put(id, book);

        HashMap<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("title", book.getTitle());
        response.put("numPages", book.getNumPages());
        response.put("author", book.getAuthor());
        response.put("genre", book.getGenre());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HashMap<String, Object>>> getAll () {
        List<HashMap<String, Object>> response = new ArrayList<>();

        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", entry.getKey());
            data.put("title", entry.getValue().getTitle());
            data.put("numPages", entry.getValue().getNumPages());
            data.put("author", entry.getValue().getAuthor());
            data.put("genre", entry.getValue().getGenre());

            response.add(data);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getOne (@PathVariable int id) {
        HashMap<String, Object> response = new HashMap<>();
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            if (entry.getKey() == id) {
                response.put("id", entry.getKey());
                response.put("title", entry.getValue().getTitle());
                response.put("numPages", entry.getValue().getNumPages());
                response.put("author", entry.getValue().getAuthor());
                response.put("genre", entry.getValue().getGenre());

                return ResponseEntity.ok(response);

            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> update (@PathVariable int id, @RequestBody Book updatedBook) {
        if (books.containsKey(id)) {
            books.put(id, updatedBook);

            HashMap<String, Object> response = new HashMap<>();
            response.put("id", id);
            response.put("title", updatedBook.getTitle());
            response.put("numPages", updatedBook.getNumPages());
            response.put("author", updatedBook.getAuthor());
            response.put("genre", updatedBook.getGenre());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> delete (@PathVariable int id) {
        if (books.containsKey(id)) {
            Book deletedBook = books.remove(id);
            HashMap<String, Object> response = new HashMap<>();
            response.put("id", id);
            response.put("title", deletedBook.getTitle());
            response.put("numPages", deletedBook.getNumPages());
            response.put("author", deletedBook.getAuthor());
            response.put("genre", deletedBook.getGenre());

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }


}
