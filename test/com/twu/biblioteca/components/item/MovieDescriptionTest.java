package com.twu.biblioteca.components.item;

import org.junit.Assert;
import org.junit.Test;

public class MovieDescriptionTest {

    private final MovieDescription movieDescription = new MovieDescription("Test movie", "Terry", "comedy", 2020, 10.0);
    private final MovieDescription anotherMovieDescription = new MovieDescription("Another test movie", "Terry", "comedy", 2020);

    @Test
    public void testProperty() {
        Assert.assertEquals("Test movie by Terry, 2020", movieDescription.getIdentifier());
        Assert.assertEquals("Test movie by Terry, 2020 (Genre: comedy, Rating: 10.0)", movieDescription.toString());
        Assert.assertEquals("Another test movie by Terry, 2020", anotherMovieDescription.getIdentifier());
        Assert.assertEquals("Another test movie by Terry, 2020 (Genre: comedy)", anotherMovieDescription.toString());
    }
}
