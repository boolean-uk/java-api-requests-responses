package com.booleanuk.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	static int bookCounter = 0;


	private int id;
	private String title;
	private int numPages;
	private String author;
	private String genre;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getNumPages() {
		return numPages;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public Book(String title, int numPages, String author, String genre) {
		this.title = title;
		this.numPages = numPages;
		this.author = author;
		this.genre = genre;
		this.id = bookCounter++;
	}
}
