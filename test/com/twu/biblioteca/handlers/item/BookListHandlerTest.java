package com.twu.biblioteca.handlers.item;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.Book;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookListHandlerTest {
    private static final Book[] books = new Book[] {
        new Book("A book", "A person", 1986),
        new Book("B book", "B person", 1992),
        new Book("C book", "C person", 1979),
        new Book("D book", "D person", 2012),
        new Book("E book", "E person", 2006),
    };

    private static final BookListHandler bookListHandler = new BookListHandler(
        new Library("Test Library")
    );

    @BeforeClass
    public static void addBookToLibrary() throws RentalItemAlreadyExistError {
        for (Book book : books) {
            bookListHandler.getLibrary().addItem(book);
        }
    }

    @Test
    public void testRetrieveOptionsMethod() throws RentalItemAlreadyExistError {
        String[] options = bookListHandler.retrieveOptions();
        Assert.assertEquals(5, options.length);
        for (int index = 0; index < 100; index++) {
            bookListHandler
                .getLibrary()
                .addItem(
                    new Book(
                        "Some book written for 100 years",
                        "People",
                        1900 + index
                    )
                );
        }
        bookListHandler.lastOptions = null;
        options = bookListHandler.retrieveOptions();
        Assert.assertEquals(15, options.length); // Only showing top 15 results
    }
}
