package com.twu.biblioteca.handlers;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.User;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Abstract input handler class for generic input parsing and delegation
 */
public abstract class InputHandler {
    public static final String EXIT_FLAG = "EXIT";
    public static final String RETURN_FLAG = "RETURN";
    public static final String CHECKOUT_ITEMS_KEY = "CHECKED_OUT";

    protected static final Scanner SCANNER = new Scanner(System.in);
    protected static User SIGNED_IN_AS = null;
    protected static HashMap<User, HashMap<String, HashMap<String, HashMap<String, Object>>>> USER_SESSIONS = new HashMap<>();

    protected static void initiateUserSession() {
        if (!USER_SESSIONS.containsKey(SIGNED_IN_AS)) {
            USER_SESSIONS.put(SIGNED_IN_AS, new HashMap<>());
            InputHandler
                .getCurrentUserSession()
                .put(CHECKOUT_ITEMS_KEY, new HashMap<>());
        }
    }

    protected static HashMap<String, HashMap<String, HashMap<String, Object>>> getCurrentUserSession() {
        return USER_SESSIONS.get(SIGNED_IN_AS);
    }

    protected static HashMap<String, HashMap<String, Object>> getCurrentUserCheckoutItems() {
        return InputHandler.getCurrentUserSession().get(CHECKOUT_ITEMS_KEY);
    }

    protected final Library library;

    protected Object[] optionReference;

    public InputHandler(Library library) {
        this.library = library;
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

    private InputHandler delegateHandler;
    private HashMap<String, InputHandler> delegateHandlerMap;
    private InputHandler previousHandler;
    private boolean optionWithIndex = true;

    /**
     * Public method to parse input to the specific input handler class
     */
    public void run(String... previousInput) {
        this.displayOptions(previousInput);

        // Parse and handling user input; delegate input to child handler or process with parent parsing method
        String userInput = SCANNER.nextLine();
        System.out.println();

        this.handleUserInput(userInput, previousInput);
    }

    /**
     * Generic method to display options with header and footer
     */
    protected void displayOptions(String... previousInput) {
        // Retrieve and display options
        this.printHeading();
        String[] options = retrieveOptions(previousInput);
        System.out.print(
            "\n" +
            InputHandler.composeOptionString(this.optionWithIndex, options)
        );
        this.printFooter();
        InputHandler.printFallbackOption(); // Print fallback options
    }

    /**
     * Generic method to handle user input
     * @param userInput User input read from scanner
     * @param previousInput Previous contextual input
     */
    protected void handleUserInput(String userInput, String... previousInput) {
        if (userInput.equals(EXIT_FLAG)) {
            System.exit(0);
        } else if (userInput.equals(RETURN_FLAG)) {
            if (this.previousHandler == null) System.exit(0);
            this.previousHandler.run();
        } else {
            this.delegateNextHandler(userInput, previousInput);
        }
    }

    /**
     * Generic method to delegate input to next handler
     * @param userInput User input read from scanner
     * @param previousInput Previous contextual input
     */
    protected void delegateNextHandler(
        String userInput,
        String... previousInput
    ) {
        String[] parseResult = this.parseInput(userInput, previousInput);
        if (this.delegateHandler != null || this.delegateHandlerMap != null) {
            InputHandler handler = this.determineDelegate(parseResult);
            handler.run(parseResult);
        } else {
            System.exit(0);
        }
    }

    /**
     * Determine next delegate to be the first occurrence by default
     */
    protected InputHandler determineDelegate(String... input) {
        if (this.delegateHandlerMap != null) {
            return this.delegateHandlerMap.get(input[0]);
        }
        return this.delegateHandler;
    }

    /**
     * Standard method to parse user input
     * Only certain flag strings and number is allowed
     */
    protected String[] parseInput(String input, String... context) {
        try {
            Integer.parseInt(input);
            return new String[] { input };
        } catch (NumberFormatException e) {
            this.redirectFromInvalidInput();
            return new String[] {  };
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
        this.delegateHandler = inputHandler;
    }

    /**
     * Set next input handler to delegate result to
     */
    public void delegateTo(HashMap<String, InputHandler> inputHandler) {
        for (InputHandler handler : inputHandler.values()) {
            handler.setPreviousHandler(this);
        }
        this.delegateHandlerMap = inputHandler;
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
     * Create option reference from string array
     */
    public void assignReference(Object[] optionReference) {
        this.optionReference = optionReference;
    }

    /**
     * Getter for library
     */
    public Library getLibrary() {
        return this.library;
    }

    /**
     * Getter for previous handler
     */
    protected InputHandler getPreviousHandler() {
        return this.previousHandler;
    }

    /**
     * Getter for delegate handler
     */
    protected InputHandler getDelegateHandler() {
        return this.delegateHandler;
    }

    /**
     * Getter for delegate handler map
     */
    protected HashMap<String, InputHandler> getDelegateHandlerMap() {
        return this.delegateHandlerMap;
    }

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
        String fallbackString =
            "(Type" +
            " " +
            EXIT_FLAG +
            " " +
            "to exit the program, or " +
            RETURN_FLAG +
            " to return to previous section)";
        System.out.println();
        System.out.println(
            InputHandler.composeOptionString(false, fallbackString)
        );
    }

    /**
     * Compose option string to be printed on console
     * @param options Array of option strings
     * @return Composed option strings ready as output stream
     */
    protected static String composeOptionString(
        Boolean withIndex,
        String... options
    ) {
        StringBuilder outputString = new StringBuilder();
        int startIndex = 1;
        for (String option : options) {
            if (withIndex) {
                outputString
                    .append("[")
                    .append(startIndex)
                    .append("]")
                    .append(" ");
            }
            outputString.append(option);
            if (startIndex != options.length) outputString.append("\n");
            startIndex++;
        }
        return outputString.toString();
    }
}
