package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.User;
import com.twu.biblioteca.handlers.InputHandler;

public class UserProfileHandler extends InputHandler {

    public UserProfileHandler(Library library) {
        super(library);
    }

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Please find your profile information below:");
    }

    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        User currentUser = SIGNED_IN_AS;
        this.setOptionWithIndex(false);
        return new String[] {
            "Name: " + currentUser.getName(),
            "Email: " + currentUser.getEmail(),
            "Library ID: " + currentUser.getIdentifier(),
        };
    }
}
