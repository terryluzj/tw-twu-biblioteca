package com.twu.biblioteca.exceptions;

/**
 * Custom non-existing rental item exception class
 */
public class RentalItemNotExistError extends Exception {
    public RentalItemNotExistError(String errorMessage) {
        super(errorMessage);
    }
}
