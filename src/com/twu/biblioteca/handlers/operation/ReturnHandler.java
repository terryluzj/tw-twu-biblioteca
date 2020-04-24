package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.RentalItem;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import com.twu.biblioteca.exceptions.RentalItemNotExistError;
import com.twu.biblioteca.handlers.InputHandler;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ReturnHandler extends InputHandler {
    private RentalItemType rentalItemType;

    public ReturnHandler(Library library) {
        super(library);
    }

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
            String itemIdentifier = (String) this.optionReference[Integer.parseInt(
                    parsedInput[0]
                ) -
                1];
            RentalItem item = library.getItemByIdentifier(
                this.rentalItemType,
                itemIdentifier
            );
            library.returnItem(item);
            if (
                !InputHandler
                    .getCurrentUserCheckoutItems()
                    .containsKey(this.rentalItemType.toString())
            ) {
                throw new NoSuchElementException("Item session not found!");
            }
            InputHandler
                .getCurrentUserCheckoutItems()
                .get(this.rentalItemType.toString())
                .remove(itemIdentifier);
            System.out.println(
                "Thank you for returning the item! Please leave a review if you like it."
            );
            this.backToTop();
            return new String[] { itemIdentifier };
        } catch (
            ArrayIndexOutOfBoundsException
            | RentalItemNotExistError
            | RentalItemAlreadyExistError e
        ) {
            System.out.println("That is not a valid item to return.");
            this.redirectFromInvalidInput();
        }
        return new String[] {  };
    }

    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        try {
            String optionType = input[1];
            this.rentalItemType = RentalItemType.valueOf(optionType);
            HashMap<String, Object> map = InputHandler
                .getCurrentUserCheckoutItems()
                .get(optionType);
            this.assignReference(map.keySet().toArray(new String[0]));
            return (String[]) this.optionReference;
        } catch (IndexOutOfBoundsException e) {
            this.redirectFromInvalidInput();
            return new String[] {  };
        }
    }
}
