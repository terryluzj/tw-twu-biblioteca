package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.handlers.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class WelcomeHandler extends InputHandler {

    public WelcomeHandler(Library library) {
        super(library);
    }

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great titles in Bangalore!");
        System.out.println("We currently have "
                + library.getAvailableItemCount(RentalItemType.BOOK) + " books and "
                + library.getAvailableItemCount(RentalItemType.MOVIE) + " movies for you to browse.");
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
        return new String[] { Integer.toString(index - 1), this.optionReference[index - 1].toString() };
    }

    /**
     * Override delegate determining method
     */
    @Override
    protected InputHandler determineDelegate(String input, String... context) {
        return this.getDelegateHandler()[Integer.parseInt(input) - 1];
    }


    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        ArrayList<String> initialOptions = new ArrayList<String>(Arrays.asList("List of books", "List of movies"));
        ArrayList<String> optionReference = new ArrayList<>(initialOptions);
        for (RentalItemType itemType: RentalItemType.values()) {
            if (
                    ITEM_SESSION.get(CHECKOUT_ITEMS_KEY).containsKey(itemType.toString()) &&
                    ITEM_SESSION.get(CHECKOUT_ITEMS_KEY).get(itemType.toString()).size() > 0)
            {
                initialOptions.add("Return " + itemType.toString().toLowerCase() + " items");
                optionReference.add(itemType.toString());
            }
        }
        this.assignReference(optionReference.toArray());
        return initialOptions.toArray(new String[0]);
    }
}
