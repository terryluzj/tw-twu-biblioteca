package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.Book;
import com.twu.biblioteca.exceptions.BookNotExistError;
import com.twu.biblioteca.exceptions.RentalItemNotExistError;
import com.twu.biblioteca.handlers.InputHandler;

public class CheckoutHandler extends InputHandler {
    public CheckoutHandler(Library library) {
        super(library);
    }

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
            } catch (RentalItemNotExistError | IndexOutOfBoundsException e) {
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
