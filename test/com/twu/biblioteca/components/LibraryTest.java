package com.twu.biblioteca.components;

import com.twu.biblioteca.components.item.Book;
import com.twu.biblioteca.components.item.Movie;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import com.twu.biblioteca.exceptions.RentalItemNotExistError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class LibraryTest {

    private final Book[] books = new Book[] {
            new Book("A book", "A person", 1986),
            new Book("B book", "B person", 1992),
            new Book("C book", "C person", 1979),
            new Book("D book", "D person", 2012),
            new Book("E book", "E person", 2006),
    };

    private final Movie[] movies = new Movie[] {
            new Movie("A movie", "A director", "horror", 1986, 7.5),
            new Movie("B movie", "B director", "comedy", 1992, 6.7),
            new Movie("C movie", "C director", "fantasy", 1979, 8.1),
            new Movie("D movies", "D director", "sci-fi", 2012, 5.6),
            new Movie("E movie", "E director", "action", 2006, 4.3),
    };

    private Library library;

    @Before
    public void addItemToLibrary() throws RentalItemAlreadyExistError {
        library = new Library("biblioteca");
        for (Book book: books) {
            library.addItem(book);
        }
        for (Movie movie: movies) {
            library.addItem(movie);
        }
    }

    @Test
    public void testProperty() {
        Assert.assertEquals("biblioteca", library.getName());

        Assert.assertEquals(5, library.getAvailableItemCount(RentalItemType.BOOK));
        Assert.assertEquals(5, library.getAvailableItems(RentalItemType.BOOK).size());

        Assert.assertEquals(5, library.getAvailableItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(5, library.getAvailableItems(RentalItemType.MOVIE).size());
    }

    @Test
    public void testGetItemByIdentifierMethod() throws RentalItemNotExistError {
        Assert.assertEquals(movies[0], library.getItemByIdentifier(RentalItemType.MOVIE, "A movie by A director, 1986"));
        Assert.assertEquals(books[0], library.getItemByIdentifier(RentalItemType.BOOK, "A book by A person, 1986"));
    }

    @Test
    public void testAddItemMethod() throws RentalItemAlreadyExistError {
        library.addItem(new Book("F book", "F person", 2018));
        Assert.assertEquals(6, library.getAvailableItemCount(RentalItemType.BOOK));
        Assert.assertEquals(6, library.getAvailableItemCount(RentalItemType.BOOK));

        library.addItem(new Movie("Test movie", "Terry", "comedy", 2020, 10.0));
        Assert.assertEquals(6, library.getAvailableItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(6, library.getAvailableItemCount(RentalItemType.MOVIE));
    }

    @Test
    public void testGetItemMethod() {
        Assert.assertEquals(library.getAvailableItems(RentalItemType.BOOK).size(), 5);
        Assert.assertEquals(library.getItemCount(RentalItemType.BOOK), 5);

        Assert.assertEquals(library.getAvailableItems(RentalItemType.MOVIE).size(), 5);
        Assert.assertEquals(library.getItemCount(RentalItemType.MOVIE), 5);
    }

    @Test(expected = RentalItemAlreadyExistError.class)
    public void testAddExistingItemMethod() throws RentalItemAlreadyExistError {
        library.addItem(new Book("E book", "E person", 2006));
    }

    @Test
    public void testCheckoutItemMethod() throws RentalItemNotExistError {
        library.checkout(books[0]); // Checkout the very first item from the book list
        Assert.assertEquals(5, library.getItemCount(RentalItemType.BOOK));
        Assert.assertEquals(4, library.getAvailableItemCount(RentalItemType.BOOK));
        Assert.assertEquals(4, library.getAvailableItems(RentalItemType.BOOK).size());

        library.checkout(new Book("E book", "E person", 2006));
        Assert.assertEquals(5, library.getItemCount(RentalItemType.BOOK));
        Assert.assertEquals(3, library.getAvailableItemCount(RentalItemType.BOOK));
        Assert.assertEquals(3, library.getAvailableItems(RentalItemType.BOOK).size());

        library.checkout(movies[0]); // Checkout the very first item from the movie list
        Assert.assertEquals(5, library.getItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(4, library.getAvailableItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(4, library.getAvailableItems(RentalItemType.MOVIE).size());

        library.checkout(new Movie("E movie", "E director", "action", 2006, 4.3));
        Assert.assertEquals(5, library.getItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(3, library.getAvailableItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(3, library.getAvailableItems(RentalItemType.MOVIE).size());
    }

    @Test(expected = RentalItemNotExistError.class )
    public void testCheckoutInvalidBookItemMethod() throws RentalItemNotExistError {
        library.checkout(new Book("H book", "H person", 2010));
    }

    @Test(expected = RentalItemNotExistError.class )
    public void testCheckoutInvalidMovieItemMethod() throws RentalItemNotExistError {
        library.checkout(new Movie("H movie", "H director", "drama", 2009, 2.4));
    }

    @Test
    public void testReturnItemMethod() throws RentalItemNotExistError, RentalItemAlreadyExistError {
        library.checkout(books[0]);
        library.checkout(books[1]);

        library.returnItem(books[0]);
        Assert.assertEquals(5, library.getItemCount(RentalItemType.BOOK));
        Assert.assertEquals(4, library.getAvailableItemCount(RentalItemType.BOOK));
        Assert.assertEquals(4, library.getAvailableItems(RentalItemType.BOOK).size());

        library.returnItem(books[1]);
        Assert.assertEquals(5, library.getItemCount(RentalItemType.BOOK));
        Assert.assertEquals(5, library.getAvailableItemCount(RentalItemType.BOOK));
        Assert.assertEquals(5, library.getAvailableItems(RentalItemType.BOOK).size());

        library.checkout(movies[0]);
        library.checkout(movies[1]);

        library.returnItem(movies[0]);
        Assert.assertEquals(5, library.getItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(4, library.getAvailableItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(4, library.getAvailableItems(RentalItemType.MOVIE).size());

        library.returnItem(movies[1]);
        Assert.assertEquals(5, library.getItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(5, library.getAvailableItemCount(RentalItemType.MOVIE));
        Assert.assertEquals(5, library.getAvailableItems(RentalItemType.MOVIE).size());
    }

    @Test(expected = RentalItemNotExistError.class)
    public void testReturnInvalidBookItemMethod() throws RentalItemNotExistError, RentalItemAlreadyExistError {
        library.checkout(books[0]);
        library.checkout(books[1]);
        library.returnItem(new Book("H book", "H person", 2010));
    }

    @Test(expected = RentalItemAlreadyExistError.class)
    public void testReturnExistingBookItemMethod() throws RentalItemNotExistError, RentalItemAlreadyExistError {
        library.checkout(books[0]);
        library.returnItem(books[0]);
        library.returnItem(books[0]);
    }

    @Test(expected = RentalItemNotExistError.class)
    public void testReturnInvalidMovieItemMethod() throws RentalItemNotExistError, RentalItemAlreadyExistError {
        library.checkout(movies[0]);
        library.checkout(movies[1]);
        library.returnItem(new Movie("H movie", "H director", "drama", 2009, 2.4));
    }

    @Test(expected = RentalItemAlreadyExistError.class)
    public void testReturnExistingMovieItemMethod() throws RentalItemNotExistError, RentalItemAlreadyExistError {
        library.checkout(movies[0]);
        library.returnItem(movies[0]);
        library.returnItem(movies[0]);
    }
}
