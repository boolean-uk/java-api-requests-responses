package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookPath {
	List<Book> books;

	public BookPath(){
		books = new ArrayList<>();
		books.add(new Book("A Game of Thrones", 780, "George R. R. Martin", "Fantasy"));
		books.add(new Book("Maze Runner", 1000, "Aner ikke", "Fantasy"));
	}

	@GetMapping
	public List<Book> getBooks(){
		return books;
	}

	@PostMapping
	public Book postBook(@RequestBody Book book){
		books.add(book);
		return book;
	}

	@GetMapping("{id}")
	public Book getBookByID(@PathVariable int id){
		for (Book b : books){
			if (b.getId() == id) return b;
		}
		return null;
	}

	@PutMapping("{id}")
	public Book putBookByID(@PathVariable int id, @RequestBody Book book){
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == id){
				books.set(i, book);
				return book;
			}
		}
		return null;

	}

	@DeleteMapping("{id}")
	public Book deleteBookById(@PathVariable int id){
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getId() == id){
				return books.remove(i);
			}
		}
		return null;
	}
}

