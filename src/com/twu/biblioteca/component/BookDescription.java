package com.twu.biblioteca.component;

/**
 * Book description class
 */
public class BookDescription extends RentalItemDescriptor {

    private final String author;
    private final int publishYear;

    /**
     * Initiate a book descriptor class
     * @param name Book name
     * @param author Book author
     * @param publishYear Book publishing year
     */
    public BookDescription(String name, String author, int publishYear) {
        super(name);
        this.author = author;
        this.publishYear = publishYear;
    }

    public String getIdentifier() { return this.toString(); }

    @Override
    public String toString() {
        return String.join(" ", super.toString(), "by", this.author) + ", " + this.publishYear;
    }
}
