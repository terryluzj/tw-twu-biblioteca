package com.twu.biblioteca.components.item;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    private final Book book = new Book("Test book", "Terry", 2020);

    @Test
    public void testProperty() {
        Assert.assertEquals(
                "Test book by Terry, 2020",
                book.getDescription().toString()
        );
        Assert.assertEquals("Test book", book.getName());
        Assert.assertEquals("Terry", book.getAuthor());
        Assert.assertEquals(2020, book.getPublicationYear());
        Assert.assertEquals(RentalItemStatus.IN_LIBRARY, book.getStatus());
    }

    @Test
    public void testSetMethod() {
        book.setStatus(RentalItemStatus.CHECKED_OUT);
        Assert.assertEquals(RentalItemStatus.CHECKED_OUT, book.getStatus());
        book.setStatus(RentalItemStatus.IN_LIBRARY);
        Assert.assertEquals(RentalItemStatus.IN_LIBRARY, book.getStatus());
    }
}
