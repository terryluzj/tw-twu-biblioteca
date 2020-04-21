package com.twu.biblioteca.component;

import java.util.HashMap;

public class Library {

    private final String name;
    private final HashMap<String, Book> bookCollection;

    /**
     * Initiate a library instance
     * @param name Library name
     */
    public Library(String name) {
        this.name = name;
        this.bookCollection = new HashMap<>();
    }

    public void addBook(Book book) {
        String identifier = book.getDescription().getIdentifier();
        this.bookCollection.put(identifier, book);
    }

    public void addBook(String bookName, String author, int year) {
        Book newBook = new Book(bookName, author, year);
        this.addBook(newBook);
    }

    public int getBookCount() {
        return this.bookCollection.size();
    }

    public int getAvailableBookCount() {
        return this.bookCollection.size();
    }

    public String getName() {
        return this.name;
    }
}

/**
 * Custom existing book resource exception class
 */
class BookAlreadyExistError extends Exception {
    public BookAlreadyExistError(String errorMessage) {
        super(errorMessage);
    }
}
