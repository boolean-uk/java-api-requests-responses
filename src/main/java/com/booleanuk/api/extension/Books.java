package com.booleanuk.api.extension;

import com.booleanuk.api.requests.Language;
import org.springframework.aot.generate.ClassNameGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("books")
public class Books {
    private List<Book> library = new ArrayList<>(){{
        add(new Book("A Game of Thrones", 780, "George R.R. Martin", "Fantasy"));
        add(new Book("Call of the Pancakes", 1000, "Bill Gates", "Action"));
    }};

    @GetMapping
    public List<Book> getAll(){
        return this.library;
    }

    @GetMapping("{id}")
    public Book getOne(@PathVariable int id){
        for (Book book : library){
            if (book.getID() == id){
                return book;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        this.library.add(book);
        return book;
    }

    @PutMapping("{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        for(int i = 0; i < library.size(); i++){
            if (library.get(i).getID() == id){
                library.get(i).setTitle(book.getTitle());
                library.get(i).setNumPages(book.getNumPages());
                library.get(i).setAuthor(book.getAuthor());
                library.get(i).setGenre(book.getGenre());

                return library.get(i);

            }
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Book delete(@PathVariable int id){
        for (Book book : library){
            if(book.getID() == id){
                library.remove(book);
                return book;
            }
        }
        return null;
    }







}
