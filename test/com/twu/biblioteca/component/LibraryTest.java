package com.twu.biblioteca.component;

import com.twu.biblioteca.exceptions.BookAlreadyExistError;
import com.twu.biblioteca.exceptions.BookNotExistError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibraryTest {

    private static final Book[] books = new Book[] {
            new Book("A book", "A person", 1986),
            new Book("B book", "B person", 1992),
            new Book("C book", "C person", 1979),
            new Book("D book", "D person", 2012),
            new Book("E book", "E person", 2006),
    };

    private final Library library = new Library("biblioteca");

    @Before
    public void addBookToLibrary() throws BookAlreadyExistError {
        for (Book book: books) {
            library.addBook(book);
        }
    }

    @Test
    public void testProperty() {
        Assert.assertEquals(library.getName(), "biblioteca");
        Assert.assertEquals(library.getBookCount(), 5);
        Assert.assertEquals(library.getAvailableBookCount(), 5);
        Assert.assertEquals(library.getAvailableBooks().size(), 5);
    }

    @Test
    public void testAddBookMethod() throws BookAlreadyExistError {
        library.addBook(new Book("F book", "F person", 2018));
        Assert.assertEquals(library.getBookCount(), 6);
        Assert.assertEquals(library.getAvailableBookCount(), 6);

        library.addBook("G book", "G person", 2016);
        Assert.assertEquals(library.getBookCount(), 7);
        Assert.assertEquals(library.getAvailableBookCount(), 7);
    }

    @Test(expected = BookAlreadyExistError.class)
    public void testAddExistingBookMethod() throws BookAlreadyExistError {
        library.addBook(new Book("E book", "E person", 2006));
    }

    @Test
    public void testCheckoutBookMethod() {
        library.checkout(books[0]); // Checkout the very first item from the book list
        Assert.assertEquals(library.getBookCount(), 5);
        Assert.assertEquals(library.getAvailableBookCount(), 4);
        Assert.assertEquals(library.getAvailableBooks().size(), 4);

        library.checkout(new Book("E book", "E person", 2006));
        Assert.assertEquals(library.getBookCount(), 5);
        Assert.assertEquals(library.getAvailableBookCount(), 3);
        Assert.assertEquals(library.getAvailableBooks().size(), 3);

        library.checkout("C book", "C person", 1979);
    }

    @Test(expected = BookNotExistError.class )
    public void testCheckoutInvalidBookMethod() {
        library.checkout(new Book("H book", "H person", 2010));
    }
}
