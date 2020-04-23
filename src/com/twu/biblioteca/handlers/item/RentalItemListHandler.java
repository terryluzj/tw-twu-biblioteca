package com.twu.biblioteca.handlers.item;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.RentalItem;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import com.twu.biblioteca.handlers.InputHandler;
import com.twu.biblioteca.utils.JSONLoader;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Rental item list handler abstract class
 */
public abstract class RentalItemListHandler extends InputHandler {

    public static final String SHUFFLE_FLAG = "SHUFFLE";
    public static int MAX_DISPLAY_ITEMS = 15;

    private final String fileName;
    private final RentalItemType rentalType;
    private final JSONArray itemData;

    private String[] lastOptions;

    /**
     * Abstract method for creating instance from loaded data
     */
    public abstract void loadItemData() throws RentalItemAlreadyExistError;

    /**
     * Abstract rental item list input handler class
     * @param library Library instance
     * @param rentalType Rental item enum type
     * @param loadFromFileName File name string
     */
    public RentalItemListHandler(Library library, RentalItemType rentalType, String loadFromFileName) {
        super(library);
        this.rentalType = rentalType;
        this.fileName = loadFromFileName;
        this.itemData = this.loadJSONData();
    }

    /**
     * Load mock data from JSON file
     * @return JSON object array
     */
    private JSONArray loadJSONData() {
        try {
            JSONLoader loader = new JSONLoader();
            return loader.load(this.fileName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    /**
     * Getter for item data
     * @return JSON object data
     */
    public JSONArray getItemData() { return this.itemData; }

    /**
     * Override parse input method
     */
    @Override
    protected String[] parseInput(String input, String... context) {
        if (input.equals(SHUFFLE_FLAG)) {
            this.lastOptions = null;
            this.run();
        }
        try {
            String[] parsedInput = super.parseInput(input);
            RentalItem itemReference = (RentalItem) this.optionReference[Integer.parseInt(parsedInput[0]) - 1];
            return new String[]{ itemReference.getDescription().getIdentifier(), itemReference.getType().toString() };
        } catch (ArrayIndexOutOfBoundsException e) {
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
        if (this.lastOptions != null) {
            return this.lastOptions;
        }

        ArrayList<RentalItem> rentalItemCollection = new ArrayList<>(this.library.getAvailableItems(this.rentalType));
        Collections.shuffle(rentalItemCollection);

        int maximumReturn = Math.min(MAX_DISPLAY_ITEMS, rentalItemCollection.size());
        String[] itemStringCollection = new String[maximumReturn];
        this.assignReference(new RentalItem[maximumReturn]);

        int count = 0;
        for (RentalItem item: rentalItemCollection) {
            itemStringCollection[count] = item.getDescription().toString();
            this.optionReference[count] = item;
            count++;
            if (count >= maximumReturn) break;
        }

        this.lastOptions = itemStringCollection;

        return itemStringCollection;
    }

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Here are some of our selections (only showing top "
                + MAX_DISPLAY_ITEMS + " "
                + this.rentalType.toString().toLowerCase() + " items)");
    }

    /**
     * Print footer message
     */
    @Override
    protected void printFooter() {
        super.printFooter();
        System.out.println("\nCurrent number of available "
                + this.rentalType.toString().toLowerCase()
                + " items : "
                + this.library.getAvailableItemCount(this.rentalType));
        System.out.println("Type a digit to check out the one you find interesting.");
        System.out.println("HINT: you can type SHUFFLE to generate a new list of items.");
    }
}
