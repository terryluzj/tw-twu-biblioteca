package com.twu.biblioteca.handlers.operation;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.User;
import com.twu.biblioteca.handlers.InputHandler;
import com.twu.biblioteca.utils.JSONLoader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

/**
 * User authentication handler class
 */
public class UserAuthHandler extends InputHandler {

    private static final String USER_FILE = "user_data.json";
    private HashMap<String, User> userPool = new HashMap<>();
    private HashMap<String, String> userCredentials = new HashMap<>();

    public UserAuthHandler(Library library) {
        super(library);
    }

    /**
     * Load user data from file
     */
    public void loadUserData() {
        try {
            JSONLoader jsonLoader = new JSONLoader();
            JSONArray userData = jsonLoader.load(USER_FILE);
            for (Object o : userData) {
                JSONObject user = (JSONObject) o;
                User newUser = new User(
                        (String) user.get("code"),
                        (String) user.get("first_name"),
                        (String) user.get("last_name"),
                        (String) user.get("email")
                );
                this.userPool.put(newUser.getIdentifier(), newUser);
                this.userCredentials.put(newUser.getIdentifier(), (String) user.get("password"));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override run method from input handler
     */
    @Override
    public void run(String... previousInput) {
        if (SIGNED_IN_AS != null) {
            this.delegateNextHandler("", previousInput);
        }

        this.printHeading();
        System.out.print("Please enter your library identification code: ");

        String code = SCANNER.nextLine();
        User user = this.findUser(code);

        if (user == null) {
            System.out.println("We couldn't find any user with this code! Please try again!\n");
            this.run();
        }
        else {
            System.out.print("Please enter your password: ");
        }

        String password = SCANNER.nextLine();
        User authenticatedUser = this.authenticateUser(code, password);

        if (authenticatedUser == null) {
            System.out.println("We couldn't authenticate you as the password is incorrect. Please try again!\n");
            this.run();
        } else {
            SIGNED_IN_AS = authenticatedUser;
            InputHandler.initiateUserSession();
            System.out.println("Welcome back " + user.getName() + "!\n");
            this.handleUserInput("");
        }
    }

    /**
     * Find a user through identification code
     * @param code User identification code
     */
    public User findUser(String code) {
        return this.userPool.get(code);
    }

    /**
     * Authenticate a user through credentials
     */
    public User authenticateUser(String code, String password) {
        String storedPassword = this.userCredentials.get(code);
        if (password.equals(storedPassword)) {
            return this.userPool.get(code);
        }
        return null;
    }


    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Hi! please enter your credentials so we can log you in.");
    }

    /**
     * Override parse input method
     */
    @Override
    protected String[] parseInput(String input, String... context) {
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
        return new String[]{ };
    }

    /**
     * Getter for user pool size
     */
    public int getUserPoolSize() {
        return this.userPool.size();
    }
}
