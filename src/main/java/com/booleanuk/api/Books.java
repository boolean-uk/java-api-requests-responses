package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class Books {
	List<Book> books = new ArrayList<>();
	static int id = 0;

	@PostMapping
	public Book create(@RequestBody Book book) {
		book.setId(id += 1);
		this.books.add(book);
		return book;
	}

	@GetMapping
	public List<Book> getAll() {
		return this.books;
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable int id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Book update(@PathVariable int id, @RequestBody Book book) {
		for (Book b : books) {
			if (b.getId() == id) {
				b.setAuthor(b.getAuthor());
				b.setGenre(b.getGenre());
				b.setTitle(book.getTitle());
				b.setNumPages(book.getNumPages());
				return b;
			}
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public Book delete(@PathVariable int id) {
		for (Book book : books) {
			if (book.getId() == id) {
				books.remove(book);
				return book;
			}
		}
		return null;
	}


}
