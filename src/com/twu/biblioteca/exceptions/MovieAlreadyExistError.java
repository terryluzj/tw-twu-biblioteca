package com.twu.biblioteca.exceptions;

/**
 * Custom existing movie resource exception class
 */
public class MovieAlreadyExistError extends RentalItemAlreadyExistError {
    public MovieAlreadyExistError(String errorMessage) {
        super(errorMessage);
    }
}
