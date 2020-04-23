package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.handlers.InputHandler;

public class WelcomeHandler extends InputHandler {

    public WelcomeHandler(Library library) {
        super(library);
    }

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("We currently have " + library.getAvailableBookCount() + " books for you to browse.");
        System.out.println("Enter a digit corresponding with the option that you want to proceed with.");
    }

    /**
     * Override parse input method
     */
    @Override
    protected String[] parseInput(String input, String... context) {
        String[] options = this.retrieveOptions(input);
        int index = Integer.parseInt(super.parseInput(input)[0]);
        if (index < 1 || index > options.length) {
            this.redirectFromInvalidInput();
        }
        return super.parseInput(input);
    }

    /**
     * Override delegate determining method
     */
    @Override
    protected InputHandler determineDelegate(String input, String... context) {
        if (this.session.get(InputHandler.CHECKOUT_ITEMS_KEY).size() > 0) {
            return this.getDelegateHandler()[Integer.parseInt(input) - 1];
        }
        return super.determineDelegate(input, context);
    }


    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        if (this.session.get(InputHandler.CHECKOUT_ITEMS_KEY).size() > 0) {
            return new String[]{"List of books", "Return books"};
        }
        return new String[]{ "List of books" };
    }
}
