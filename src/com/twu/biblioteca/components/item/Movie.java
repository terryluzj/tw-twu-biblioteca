package com.twu.biblioteca.components.item;

import java.security.InvalidParameterException;

public class Movie extends RentalItem {

    private final String director;
    private final String genre;
    private final int airedIn;
    private final MovieDescription description;
    private double rating;

    /**
     * Initiate a movie class
     * @param director Movie director name
     * @param genre Movie genre in string form
     * @param airedIn Year the movie was aired
     * @param rating Movie rating
     */
    public Movie(String name, String director, String genre, int airedIn, double rating) {
        super(name, RentalItemType.MOVIE);
        this.director = director;
        this.genre = genre;
        this.airedIn = airedIn;

        if (rating < 0 || rating > 10) {
            throw new InvalidParameterException("Rating should be between 0 and 10.");
        }

        this.rating = rating;
        this.description = new MovieDescription(name, director, genre, airedIn, rating);
    }

    /**
     * Initiate a movie class
     * @param director Movie director name
     * @param genre Movie genre in string form
     * @param airedIn Year the movie was aired
     */
    public Movie(String name, String director, String genre, int airedIn) {
        super(name, RentalItemType.MOVIE);
        this.director = director;
        this.genre = genre;
        this.airedIn = airedIn;
        this.description = new MovieDescription(name, director, genre, airedIn);
    }

    public MovieDescription getDescription() {
        return this.description;
    }

    public String getDirector() {
        return this.director;
    }

    public String getGenre() { return this.genre; }

    public int getAiredInYear() {
        return this.airedIn;
    }

    public double getRating() { return this.rating; }
}
