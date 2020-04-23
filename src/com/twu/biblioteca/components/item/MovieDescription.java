package com.twu.biblioteca.components.item;

/**
 * Movie description class
 */
public class MovieDescription extends RentalItemDescriptor {

    private final String director;
    private final String genre;
    private final int airedIn;
    private double rating = -1;

    /**
     * Initiate a movie class
     * @param name Movie name
     * @param director Movie director name
     * @param genre Movie genre in string form
     * @param airedIn Year the movie was aired
     */
    public MovieDescription(String name, String director, String genre, int airedIn) {
        super(name);
        this.director = director;
        this.genre = genre;
        this.airedIn = airedIn;
    }

    /**
     * Initiate a movie class
     * @param name Movie name
     * @param director Movie director name
     * @param genre Movie genre in string form
     * @param airedIn Year the movie was aired
     * @param rating Movie rating
     */
    public MovieDescription(String name, String director, String genre, int airedIn, double rating) {
        super(name);
        this.director = director;
        this.genre = genre;
        this.airedIn = airedIn;
        this.rating = rating;
    }

    public String getIdentifier() { return this.getBaseString(); }

    @Override
    public String toString() {
        String baseString = this.getBaseString();
        if (this.rating >= 0) {
            return baseString + " (" + "Genre: " + this.genre + ", Rating: " + this.rating + ")";
        }
        else return baseString + " (" + "Genre: " + this.genre + ")";
    }

    private String getBaseString() {
        return String.join(" ", super.toString(), "by", this.director) + ", " + this.airedIn;
    }
}
