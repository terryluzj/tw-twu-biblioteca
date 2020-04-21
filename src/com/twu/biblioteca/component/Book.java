package com.twu.biblioteca.component;

public class Book {

    private final String name;
    private final String author;
    private final int publishYear;
    private final BookDescription description;
    private BookStatus bookStatus;

    /**
     * Initiate a book descriptor class
     * @param name Book name
     * @param author Book author
     * @param publishYear Book publishing year
     */
    public Book(String name, String author, int publishYear) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.description = new BookDescription(name, author, publishYear);
        this.bookStatus = BookStatus.IN_LIBRARY;
    }

    public BookDescription getDescription() {
        return this.description;
    }

    public BookStatus getStatus() {
        return this.bookStatus;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublicationYear() {
        return this.publishYear;
    }

    public void setStatus(BookStatus status) {
        this.bookStatus = status;
    }
}
