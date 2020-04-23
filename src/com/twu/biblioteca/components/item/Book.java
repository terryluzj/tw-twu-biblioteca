package com.twu.biblioteca.components.item;

public class Book extends RentalItem {

    private final String author;
    private final int publishYear;
    private final BookDescription description;

    /**
     * Initiate a book class
     * @param name Book name
     * @param author Book author
     * @param publishYear Book publishing year
     */
    public Book(String name, String author, int publishYear) {
        super(name, RentalItemType.BOOK);
        this.author = author;
        this.publishYear = publishYear;
        this.description = new BookDescription(name, author, publishYear);
    }

    public BookDescription getDescription() {
        return this.description;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublicationYear() {
        return this.publishYear;
    }
}
