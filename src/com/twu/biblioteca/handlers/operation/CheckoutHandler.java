package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.RentalItem;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.exceptions.RentalItemNotExistError;
import com.twu.biblioteca.handlers.InputHandler;

import java.util.HashMap;

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
                RentalItem item = library.getItemByIdentifier(RentalItemType.valueOf(context[1]), context[0]);
                String itemType = item.getType().toString();
                library.checkout(item);
                if (!ITEM_SESSION.get(CHECKOUT_ITEMS_KEY).containsKey(itemType)) {
                    ITEM_SESSION.get(CHECKOUT_ITEMS_KEY).put(itemType, new HashMap<>());
                }
                ITEM_SESSION.get(CHECKOUT_ITEMS_KEY).get(itemType).put(item.getDescription().getIdentifier(), item);
                System.out.println("Thank you! Enjoy " + item.getName() + "!");
                this.backToTop();
            } catch (RentalItemNotExistError | IndexOutOfBoundsException e) {
                System.out.println("You cannot checkout this item. (" + e.getMessage() + ")");
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
        return new String[] { input[0], "[Y] Yes", "[N] No" };
    }
}
