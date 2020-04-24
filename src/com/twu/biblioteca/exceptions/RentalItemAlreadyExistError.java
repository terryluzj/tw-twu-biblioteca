package com.twu.biblioteca.exceptions;

/**
 * Custom existing item exception class
 */
public class RentalItemAlreadyExistError extends Exception {

    public RentalItemAlreadyExistError(String errorMessage) {
        super(errorMessage);
    }
}
