package com.booleanuk.api.requests;

import java.util.ArrayList;
import java.util.List;

public class Books {

    private List<Book> books;

    public Books() {
        this.books = new ArrayList<>();
        this.books.add(new Book("BOOK!", 150, "Writer", "Action"));
    }


}
