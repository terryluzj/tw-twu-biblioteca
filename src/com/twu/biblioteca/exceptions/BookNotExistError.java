package com.twu.biblioteca.exceptions;

/**
 * Custom non-existing book resource exception class
 */
public class BookNotExistError extends Exception {
    public BookNotExistError(String errorMessage) {
        super(errorMessage);
    }
}
