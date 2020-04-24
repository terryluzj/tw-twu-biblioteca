package com.twu.biblioteca.handlers.item;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.Movie;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MovieListHandlerTest {
    private static final Movie[] movies = new Movie[] {
        new Movie("A movie", "A director", "horror", 1986, 7.5),
        new Movie("B movie", "B director", "comedy", 1992, 6.7),
        new Movie("C movie", "C director", "fantasy", 1979, 8.1),
        new Movie("D movie", "D director", "sci-fi", 2012, 5.6),
        new Movie("E movie", "E director", "action", 2006, 4.3),
    };

    private static final MovieListHandler movieListHandler = new MovieListHandler(
        new Library("Test Library")
    );

    @BeforeClass
    public static void addMovieToLibrary() throws RentalItemAlreadyExistError {
        for (Movie movie : movies) {
            movieListHandler.getLibrary().addItem(movie);
        }
    }

    @Test
    public void testRetrieveOptionsMethod() throws RentalItemAlreadyExistError {
        String[] options = movieListHandler.retrieveOptions();
        Assert.assertEquals(5, options.length);
        for (int index = 0; index < 100; index++) {
            movieListHandler
                .getLibrary()
                .addItem(
                    new Movie(
                        "Some movie performed for 100 years",
                        "People",
                        "drama",
                        1900 + index,
                        Math.random() * 10
                    )
                );
        }
        movieListHandler.lastOptions = null;
        options = movieListHandler.retrieveOptions();
        Assert.assertEquals(15, options.length); // Only showing top 15 results
    }
}
