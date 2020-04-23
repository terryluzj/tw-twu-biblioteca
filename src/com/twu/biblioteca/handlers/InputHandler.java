package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.Library;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Abstract input handler class for generic input parsing and delegation
 */
public abstract class InputHandler {

    public static final String EXIT_FLAG = "EXIT";
    public static final String RETURN_FLAG = "RETURN";
    public static final String CHECKOUT_ITEMS_KEY = "CHECKED_OUT";

    protected static final HashMap<String, HashMap<String, Object>> itemSession = new HashMap<>();
    protected static final Scanner scanner = new Scanner(System.in);

    protected final Library library;
    protected final HashMap<String, HashMap<String, Object>> session;

    public InputHandler(Library library) {
        this.library = library;
        this.session = itemSession;
        this.session.put(CHECKOUT_ITEMS_KEY, new HashMap<>());
    }

    /**
     * Print heading message
     */
    protected abstract void printHeading();

    /**
     * Print footer message
     */
    protected void printFooter() {
        System.out.println();
    }

    /**
     * Retrieve options given optional user input
     * @param input User input
     * @return Array of options in string form
     */
    protected abstract String[] retrieveOptions(String... input);

    private InputHandler[] delegateHandler;
    private InputHandler previousHandler;
    private boolean optionWithIndex = true;

    /**
     * Public method to parse input to the specific input handler class
     */
    public void run(String... previousInput) {
        // Retrieve and display options
        this.printHeading();
        String[] options = retrieveOptions(previousInput);
        System.out.print("\n" + InputHandler.composeOptionString(this.optionWithIndex, options));
        this.printFooter();

        InputHandler.printFallbackOption(); // Print fallback options

        // Parse and handling user input; delegate input to child handler or process with parent parsing method
        String userInput = scanner.nextLine();
        System.out.println();

        if (userInput.equals(EXIT_FLAG)) {
            System.exit(0);
        }
        else if (userInput.equals(RETURN_FLAG)) {
            if (this.previousHandler == null) System.exit(0);
            this.previousHandler.run();
        } else {
            String[] parseResult = this.parseInput(userInput, previousInput);
            if (this.delegateHandler != null) {
                InputHandler handler = this.determineDelegate(userInput, previousInput);
                handler.run(parseResult);
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * Determine next delegate to be the first occurrence by default
     */
    protected InputHandler determineDelegate(String input, String... context) {
        return this.delegateHandler[0];
    }

    /**
     * Standard method to parse user input
     * Only certain flag strings and number is allowed
     */
    protected String[] parseInput(String input, String... context) {
        try {
            Integer.parseInt(input);
            return new String[]{ input };
        }
        catch (NumberFormatException e) {
            this.redirectFromInvalidInput();
            return new String[]{ };
        }
    }

    /**
     * Print message based on invalid input and run again
     */
    protected void redirectFromInvalidInput() {
        System.out.println("Invalid input. Please select a valid options!");
        this.run();
    }

    /**
     * Set next input handler to delegate result to
     */
    public void delegateTo(InputHandler inputHandler) {
        inputHandler.setPreviousHandler(this);
        this.delegateHandler = new InputHandler[] { inputHandler };
    }

    /**
     * Set next input handler to delegate result to
     */
    public void delegateTo(InputHandler[] inputHandler) {
        for (InputHandler handler: inputHandler) {
            handler.setPreviousHandler(this);
        }
        this.delegateHandler = inputHandler;
    }

    /**
     * Go back to the very first handler
     */
    public void backToTop() {
        InputHandler currentHandler = this;
        while (currentHandler.previousHandler != null) {
            currentHandler = currentHandler.previousHandler;
        }
        currentHandler.run();
    }

    /**
     * Getter for previous handler
     */
    protected InputHandler getPreviousHandler() { return this.previousHandler; }

    /**
     * Getter for delegate handler
     */
    protected InputHandler[] getDelegateHandler() { return this.delegateHandler.clone(); }

    /**
     * Set boolean flag to compose options with or without index
     */
    public void setOptionWithIndex(boolean flag) {
        this.optionWithIndex = flag;
    }

    /**
     * Set previous input handler to go back
     * @param handler Input handler upon returning back action
     */
    public void setPreviousHandler(InputHandler handler) {
        this.previousHandler = handler;
    }

    /**
     * Print fallback option
     */
    protected static void printFallbackOption() {
        String fallbackString = "(Type" + " " + EXIT_FLAG + " " + "to exit the program, or " + RETURN_FLAG + " to return to previous section)";
        System.out.println();
        System.out.println(InputHandler.composeOptionString(false, fallbackString));
    }

    /**
     * Compose option string to be printed on console
     * @param options Array of option strings
     * @return Composed option strings ready as output stream
     */
    protected static String composeOptionString(Boolean withIndex, String ... options) {
        StringBuilder outputString = new StringBuilder();
        int startIndex = 1;
        for (String option: options) {
            if (withIndex) {
                outputString.append("[").append(startIndex).append("]").append(" ");
            }
            outputString.append(option);
            if (startIndex != options.length) outputString.append("\n");
            startIndex++;
        }
        return outputString.toString();
    }
}