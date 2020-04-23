package com.twu.biblioteca.component.item;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    private final Book book = new Book("Test book", "Terry", 2020);

    @Test
    public void testProperty() {
        Assert.assertEquals(
                book.getDescription().toString(),
                new BookDescription("Test book", "Terry", 2020).toString()
        );
        Assert.assertEquals(book.getName(), "Test book");
        Assert.assertEquals(book.getAuthor(), "Terry");
        Assert.assertEquals(book.getPublicationYear(), 2020);
        Assert.assertEquals(book.getStatus(), RentalItemStatus.IN_LIBRARY);
    }

    @Test
    public void testSetMethod() {
        book.setStatus(RentalItemStatus.CHECKED_OUT);
        Assert.assertEquals(book.getStatus(), RentalItemStatus.CHECKED_OUT);
        book.setStatus(RentalItemStatus.IN_LIBRARY);
        Assert.assertEquals(book.getStatus(), RentalItemStatus.IN_LIBRARY);
    }
}
