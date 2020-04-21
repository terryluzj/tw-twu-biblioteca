package com.twu.biblioteca.component;

import com.twu.biblioteca.exceptions.BookAlreadyExistError;
import com.twu.biblioteca.exceptions.BookNotExistError;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Library {

    private final String name;
    private final HashMap<String, Book> bookCollection;
    private int bookCount = 0;

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
        this.bookCount++;
    }

    public void addBook(String bookName, String author, int year) throws BookAlreadyExistError {
        Book newBook = new Book(bookName, author, year);
        this.addBook(newBook);
    }

    public void checkout(Book book) throws BookNotExistError {
        String identifier = book.getDescription().getIdentifier();
        if (!this.bookCollection.containsKey(identifier)) {
            throw new BookNotExistError("The book does not exist in the library!");
        }
        Book bookInLibrary = this.bookCollection.get(identifier);
        if (bookInLibrary.getStatus().equals(BookStatus.CHECKED_OUT)) {
            throw new BookNotExistError("You cannot check out this book as it is already been checked out.");
        }
        bookInLibrary.setStatus(BookStatus.CHECKED_OUT);
    }

    public void checkout(String bookName, String author, int year) throws BookNotExistError {
        Book bookObject = new Book(bookName, author, year);
        this.checkout(bookObject);
    }

    public void returnBook(Book book) throws BookNotExistError, BookAlreadyExistError {
        String identifier = book.getDescription().getIdentifier();
        if (!this.bookCollection.containsKey(identifier)) {
            throw new BookNotExistError("There is no record of this book in the library!");
        }
        Book bookInLibrary = this.bookCollection.get(identifier);
        if (bookInLibrary.getStatus().equals(BookStatus.IN_LIBRARY)) {
            throw new BookAlreadyExistError("The book is in librabry!");
        }
        bookInLibrary.setStatus(BookStatus.IN_LIBRARY);
    }

    public void returnBook(String bookName, String author, int year) throws BookNotExistError, BookAlreadyExistError {
        Book bookObject = new Book(bookName, author, year);
        this.returnBook(bookObject);
    }

    public int getBookCount() {
        return this.bookCount;
    }

    public int getAvailableBookCount() {
        return this.getAvailableBooks().size();
    }

    public Collection<Book> getAvailableBooks() {
        return this.bookCollection
                .values()
                .stream()
                .filter(book -> book.getStatus().equals(BookStatus.IN_LIBRARY))
                .collect(Collectors.toList());
    }

    public String getName() {
        return this.name;
    }
}

