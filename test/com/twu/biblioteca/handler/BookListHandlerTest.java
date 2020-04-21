package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.Book;
import com.twu.biblioteca.component.BookAlreadyExistError;
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

    private final BookListHandler bookListHandler = new BookListHandler();

    @BeforeClass
    public static void addBookToLibrary() throws BookAlreadyExistError {
        for (Book book: books) {
            BookListHandler.library.addBook(book);
        }
    }

    @Test
    public void testRetrieveOptions() throws BookAlreadyExistError {
        String[] options = bookListHandler.retrieveOptions();
        Assert.assertEquals(5, options.length);
        for (int index = 0; index <= 100; index++) {
            BookListHandler.library.addBook(new Book("Some book written for 100 years", "People", 1900 + index));
        }
        options = bookListHandler.retrieveOptions();
        Assert.assertEquals(BookListHandler.MAX_DISPLAY_ITEMS, options.length); // Only showing top 20 results
    }
}
