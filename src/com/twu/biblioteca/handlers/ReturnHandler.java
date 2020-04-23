package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.item.Book;
import com.twu.biblioteca.exceptions.BookAlreadyExistError;
import com.twu.biblioteca.exceptions.BookNotExistError;

import java.util.HashMap;

public class ReturnHandler extends InputHandler {

    private String[] bookIdentifierReference;

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("You can return the following titles:");
    }

    /**
     * Override parse input method
     */
    @Override
    protected String[] parseInput(String input, String... context) {
        try {
            String[] parsedInput = super.parseInput(input);
            String bookIdentifier = this.bookIdentifierReference[Integer.parseInt(parsedInput[0]) - 1];
            Book book = library.getBookByIdentifier(bookIdentifier);
            library.returnBook(book);
            this.session.get(InputHandler.CHECKOUT_ITEMS_KEY).remove(bookIdentifier);
            System.out.println("Thank you for returning the book! Please leave a review if you like it.");
            this.backToTop();
            return new String[]{ bookIdentifier };
        } catch (ArrayIndexOutOfBoundsException | BookNotExistError | BookAlreadyExistError e) {
            System.out.println("That is not a valid book to return");
            this.redirectFromInvalidInput();
        }
        return new String[]{ };
    }

    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        HashMap<String, Object> map = this.session.get(InputHandler.CHECKOUT_ITEMS_KEY);
        this.bookIdentifierReference = this.session.get(InputHandler.CHECKOUT_ITEMS_KEY).keySet().toArray(new String[map.size()]);
        return this.bookIdentifierReference;
    }
}
