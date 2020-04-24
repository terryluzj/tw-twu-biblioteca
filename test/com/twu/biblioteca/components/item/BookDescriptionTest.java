package com.twu.biblioteca.components.item;

import org.junit.Assert;
import org.junit.Test;

public class BookDescriptionTest {
    private final BookDescription bookDescription = new BookDescription(
        "A book",
        "A person",
        2020
    );

    @Test
    public void testProperty() {
        Assert.assertEquals(
            "A book by A person, 2020",
            bookDescription.getIdentifier()
        );
        Assert.assertEquals(
            "A book by A person, 2020",
            bookDescription.toString()
        );
    }
}
