package com.twu.biblioteca.exceptions;

/**
 * Custom existing book resource exception class
 */
public class BookAlreadyExistError extends Exception {
    public BookAlreadyExistError(String errorMessage) {
        super(errorMessage);
    }
}
