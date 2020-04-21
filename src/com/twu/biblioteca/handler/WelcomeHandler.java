package com.twu.biblioteca.handler;

public class WelcomeHandler extends InputHandler {

    @Override
    protected String[] parseInput(String input) {
        return new String[] { input };
    }

    @Override
    protected void printHeading() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("Enter a digit corresponding with the option that you want to proceed with.");
    }

    @Override
    protected String[] retrieveOptions(String... input) {
        return new String[] { "List of books" };
    }
}
