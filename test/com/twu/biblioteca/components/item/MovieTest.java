package com.twu.biblioteca.components.item;

import java.security.InvalidParameterException;
import org.junit.Assert;
import org.junit.Test;

public class MovieTest {
    private final Movie movie = new Movie(
        "Test movie",
        "Terry",
        "comedy",
        2020,
        10.0
    );

    @Test
    public void testProperty() {
        Assert.assertEquals(
            "Test movie by Terry, 2020 (Genre: comedy, Rating: 10.0)",
            movie.getDescription().toString()
        );
        Assert.assertEquals("Test movie", movie.getName());
        Assert.assertEquals("Terry", movie.getDirector());
        Assert.assertEquals(2020, movie.getAiredInYear());
        Assert.assertEquals("comedy", movie.getGenre());
        Assert.assertEquals(10.0, movie.getRating(), 0.0);
        Assert.assertEquals(RentalItemStatus.IN_LIBRARY, movie.getStatus());
    }

    @Test(expected = InvalidParameterException.class)
    public void testInvalidRating() {
        new Movie("Test movie", "Terry", "tragedy", 2020, -20.0);
    }

    @Test
    public void testSetMethod() {
        movie.setStatus(RentalItemStatus.CHECKED_OUT);
        Assert.assertEquals(RentalItemStatus.CHECKED_OUT, movie.getStatus());
        movie.setStatus(RentalItemStatus.IN_LIBRARY);
        Assert.assertEquals(RentalItemStatus.IN_LIBRARY, movie.getStatus());
    }
}
