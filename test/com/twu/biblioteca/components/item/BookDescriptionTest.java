package com.twu.biblioteca.component.item;

import org.junit.Assert;
import org.junit.Test;

public class BookDescriptionTest {

    private final BookDescription bookDescription = new BookDescription("A book", "A person", 2020);

    @Test
    public void testProperty() {
        Assert.assertEquals(bookDescription.getIdentifier(), "A book by A person, 2020");
        Assert.assertEquals(bookDescription.toString(), "A book by A person, 2020");
    }
}
