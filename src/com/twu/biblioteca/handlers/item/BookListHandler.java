package com.twu.biblioteca.handlers.item;

import com.twu.biblioteca.components.Library;
import com.twu.biblioteca.components.item.Book;
import com.twu.biblioteca.components.item.RentalItemType;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import org.json.simple.JSONObject;

public class BookListHandler extends RentalItemListHandler {

    public BookListHandler(Library library) {
        super(library, RentalItemType.BOOK, "book_data.json");
    }

    /**
     * Method for creating instance from loaded data
     */
    @Override
    public void loadItemData() {
        for (Object o : this.getItemData()) {
            JSONObject book = (JSONObject) o;
            try {
                this.getLibrary()
                    .addItem(
                        new Book(
                            (String) book.get("book_name"),
                            (String) book.get("author"),
                            Math.toIntExact((Long) book.get("publication_year"))
                        )
                    );
            } catch (RentalItemAlreadyExistError rentalItemAlreadyExistError) {
                rentalItemAlreadyExistError.printStackTrace();
            }
        }
    }
}
