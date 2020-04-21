package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.Book;
import com.twu.biblioteca.component.Library;

import java.util.Collection;

public class BookListHandler extends InputHandler {

    protected static final Library library = new Library("Test library");

    /**
     * Print heading message
     */
    @Override
    protected void printHeading() {
        System.out.println("Here are some of our selections:");
    }

    /**
     * Retrieve options given optional user input
     *
     * @param input User input
     * @return Array of options in string form
     */
    @Override
    protected String[] retrieveOptions(String... input) {
        Collection<Book> bookCollection = library.getAvailableBooks();

        int maximumReturn = Math.min(20, bookCollection.size());
        String[] bookStringCollection = new String[maximumReturn];

        int count = 0;
        for (Book book: bookCollection) {
            bookStringCollection[count] = book.getDescription().toString();
            count++;
            if (count >= maximumReturn) break;
        }

        return bookStringCollection;
    }
}
