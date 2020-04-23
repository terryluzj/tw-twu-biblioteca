package com.twu.biblioteca.exceptions;

/**
 * Custom non-existing movie resource exception class
 */
public class MovieNotExistError extends RentalItemNotExistError {
    public MovieNotExistError(String errorMessage) {
        super(errorMessage);
    }
}
