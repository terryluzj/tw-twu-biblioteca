package com.twu.biblioteca.component;

import java.util.Collection;
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

    public void addBook(Book book) throws BookAlreadyExistError {
        String identifier = book.getDescription().getIdentifier();
        if (this.bookCollection.containsKey(identifier)) {
            throw new BookAlreadyExistError("The book already exist in the library!");
        }
        this.bookCollection.put(identifier, book);
    }

    public void addBook(String bookName, String author, int year) throws BookAlreadyExistError {
        Book newBook = new Book(bookName, author, year);
        this.addBook(newBook);
    }

    public int getBookCount() {
        return this.bookCollection.size();
    }

    public int getAvailableBookCount() {
        return this.bookCollection.size();
    }

    public Collection<Book> getAvailableBooks() {
        return this.bookCollection.values();
    }

    public String getName() {
        return this.name;
    }
}

