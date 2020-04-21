package com.twu.biblioteca.handler;

import com.twu.biblioteca.component.Book;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookListHandlerTest {

    BookListHandler bookListHandler = new BookListHandler();

    @BeforeClass
    public static void addBookToLibrary() {
        BookListHandler.library.addBook(new Book("A book", "A person", 1986));
        BookListHandler.library.addBook(new Book("B book", "B person", 1992));
        BookListHandler.library.addBook(new Book("C book", "C person", 1979));
        BookListHandler.library.addBook(new Book("D book", "D person", 2012));
        BookListHandler.library.addBook(new Book("E book", "E person", 2006));
    }

    @Test
    public void testRetrieveOptions() {
        String[] options = bookListHandler.retrieveOptions();
        Assert.assertEquals(5, options.length);
        for (int index = 0; index <= 100; index++) {
            BookListHandler.library.addBook(new Book("Some book written for 100 years", "People", 1900 + index));
        }
        options = bookListHandler.retrieveOptions();
        Assert.assertEquals(20, options.length); // Only showing top 20 results
    }
}
