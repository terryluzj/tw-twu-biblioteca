package com.twu.biblioteca.components;

public class User {
    private final String identifier;
    private final String name;
    private final String email;

    /**
     * Initiate a new user
     * @param code User identifier
     * @param firstName User first name
     * @param lastName User last name
     * @param email User email
     */
    public User(String code, String firstName, String lastName, String email) {
        this.identifier = code;
        this.name = String.join(" ", firstName, lastName);
        this.email = email;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}
