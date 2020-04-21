package com.twu.biblioteca.handler;

public class WelcomeHandler extends InputHandler {

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("Enter a digit corresponding with the option that you want to proceed with.");
    }

    /**
     * Override parse input method
     */
    @Override
    protected String[] parseInput(String input) {
        if (!input.equals("1")) {
            redirectFromInvalidInput();
        }
        return super.parseInput(input);
    }


    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        return new String[]{ "List of books" };
    }
}
