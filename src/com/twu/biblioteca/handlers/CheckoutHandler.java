package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.item.Book;
import com.twu.biblioteca.exceptions.BookNotExistError;

public class CheckoutHandler extends InputHandler{
    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("You're checking out the following item, please confirm by entering Y or N:");
    }

    /**
     * Override parse input method
     */
    @Override
    protected String[] parseInput(String input, String... context) {
        // Trigger confirmation intent for user
        if (input.equals("Y")) {
            try {
                Book book = library.getBookByIdentifier(context[0]);
                library.checkout(book);
                this.session.get(InputHandler.CHECKOUT_ITEMS_KEY).put(book.getDescription().getIdentifier(), true);
                System.out.println("Thank you! Enjoy the book by " + book.getAuthor() + "!");
                this.backToTop();
            } catch (BookNotExistError | IndexOutOfBoundsException e) {
                System.out.println("You cannot checkout this book. (" + e.getMessage() + ")");
                this.getPreviousHandler().run();
            }
        } else if (input.equals("N")) {
            this.getPreviousHandler().run();
        }
        else {
            this.run(context);
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
        this.setOptionWithIndex(false);
        return new String[] { "Book: " + input[0], "[Y] Yes", "[N] No" };
    }
}
