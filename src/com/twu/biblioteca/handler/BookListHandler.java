package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.Library;

public class BookListHandler extends InputHandler {

    protected static final Library library = new Library("Test library");

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {

    }

    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        return new String[0];
    }
}
