package com.twu.biblioteca.component;

/**
 * Custom existing book resource exception class
 */
public class BookAlreadyExistError extends Exception {
    public BookAlreadyExistError(String errorMessage) {
        super(errorMessage);
    }
}
