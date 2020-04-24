package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.handlers.InputHandler;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class WelcomeHandler extends InputHandler {

    public WelcomeHandler(Library library) {
        super(library);
    }

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println(
            "Welcome to Biblioteca. Your one-stop-shop for great titles in Bangalore!"
        );
        System.out.println(
            "We currently have " +
            library.getAvailableItemCount(RentalItemType.BOOK) +
            " books and " +
            library.getAvailableItemCount(RentalItemType.MOVIE) +
            " movies for you to browse."
        );
        System.out.println(
            "Enter a digit corresponding with the option that you want to proceed with."
        );
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
        if (options[index - 1].equals("Log out")) {
            SIGNED_IN_AS = null;
            this.backToTop();
        }
        return new String[] {
            options[index - 1],
            this.optionReference[index - 1].toString(),
        };
    }

    /**
     * Override delegate determining method
     */
    @Override
    protected InputHandler determineDelegate(String... input) {
        if (this.getDelegateHandlerMap() == null) {
            throw new InvalidParameterException(
                "A welcome handler must have a delegate handler map."
            );
        }
        HashMap<String, InputHandler> handlerMap = this.getDelegateHandlerMap();
        return handlerMap.get(input[0]);
    }

    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        ArrayList<String> initialOptions = new ArrayList<String>();
        ArrayList<String> optionReference = new ArrayList<>(initialOptions);

        for (RentalItemType itemType : RentalItemType.values()) {
            String displayString =
                "List of " + itemType.toString().toLowerCase() + "s";
            initialOptions.add(displayString);
            optionReference.add(displayString);
        }

        for (RentalItemType itemType : RentalItemType.values()) {
            if (
                InputHandler
                    .getCurrentUserCheckoutItems()
                    .containsKey(itemType.toString()) &&
                InputHandler
                    .getCurrentUserCheckoutItems()
                    .get(itemType.toString())
                    .size() >
                0
            ) {
                initialOptions.add(
                    "Return " + itemType.toString().toLowerCase() + "s"
                );
                optionReference.add(itemType.toString());
            }
        }

        initialOptions.add("View my profile");
        optionReference.add("View my profile");
        initialOptions.add("Log out");
        optionReference.add("Log out");

        this.assignReference(optionReference.toArray());
        return initialOptions.toArray(new String[0]);
    }
}
