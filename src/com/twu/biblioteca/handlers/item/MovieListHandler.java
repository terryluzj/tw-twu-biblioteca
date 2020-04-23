package com.twu.biblioteca.handlers.item;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.Movie;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import org.json.simple.JSONObject;

public class MovieListHandler extends RentalItemListHandler {

    public MovieListHandler(Library library) {
        super(library, RentalItemType.MOVIE, "movie_data.json");
    }

    /**
     * Method for creating instance from loaded data
     */
    @Override
    public void loadItemData() {
        for (Object o : this.getItemData()) {
            JSONObject movie = (JSONObject) o;
            try {
                if (movie.get("rating") != null) {
                    this.getLibrary().addItem(new Movie(
                            (String) movie.get("movie_title"),
                            (String) movie.get("director"),
                            (String) movie.get("genre"),
                            Math.toIntExact((Long) movie.get("year")),
                            (Double) movie.get("rating"))
                    );
                } else {
                    this.getLibrary().addItem(new Movie(
                            (String) movie.get("movie_title"),
                            (String) movie.get("director"),
                            (String) movie.get("genre"),
                            Math.toIntExact((Long) movie.get("year")))
                    );
                }
            } catch (RentalItemAlreadyExistError rentalItemAlreadyExistError) {
                rentalItemAlreadyExistError.printStackTrace();
            }
        }
    }
}
