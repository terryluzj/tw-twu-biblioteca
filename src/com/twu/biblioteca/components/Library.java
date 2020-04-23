package com.twu.biblioteca.components;

import com.twu.biblioteca.components.item.*;
import com.twu.biblioteca.exceptions.BookAlreadyExistError;
import com.twu.biblioteca.exceptions.BookNotExistError;
import com.twu.biblioteca.exceptions.RentalItemAlreadyExistError;
import com.twu.biblioteca.exceptions.RentalItemNotExistError;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Library {

    private final String name;
    private final HashMap<String, Book> bookCollection;
    private final HashMap<RentalItemType, HashMap<String, RentalItem>> itemCollection;
    private final HashMap<RentalItemType, Integer> itemCollectionCount;
    private int bookCount = 0;

    /**
     * Initiate a library instance
     * @param name Library name
     */
    public Library(String name) {
        this.name = name;
        this.bookCollection = new HashMap<>();
        this.itemCollection = new HashMap<>();
        this.itemCollectionCount = new HashMap<>();
    }

    /**
     * Add a rental item in the library
     * @param item Rental item object
     * @throws RentalItemAlreadyExistError Item exist error
     */
    public void addItem(RentalItem item) throws RentalItemAlreadyExistError {
        RentalItemDescriptor descriptor = item.getDescription();
        String identifier = descriptor.getIdentifier();
        RentalItemType itemType = item.getType();
        if (!itemCollection.containsKey(itemType)) {
            this.itemCollection.put(itemType, new HashMap<>());
            this.itemCollectionCount.put(itemType, 0);
        }
        HashMap<String, RentalItem> items = this.itemCollection.get(item.getType());
        if (items.containsKey(identifier)) {
            throw new RentalItemAlreadyExistError("This item already exists in the library!");
        }
        this.itemCollectionCount.put(itemType, this.itemCollectionCount.get(itemType) + 1);
        items.put(identifier, item);
    }

    /**
     * Get available item from specific item type
     * @param itemType Item type
     */
    public Collection<RentalItem> getAvailableItems(RentalItemType itemType) {
        return this.itemCollection
                .get(itemType)
                .values()
                .stream()
                .filter(book -> book.getStatus().equals(RentalItemStatus.IN_LIBRARY))
                .collect(Collectors.toList());
    }

    /**
     * Get item by item identifier
     * @param itemType Item type
     * @param itemIdentifier Item identifier
     * @return Rental item in record
     */
    public RentalItem getItemByIdentifier(RentalItemType itemType, String itemIdentifier) throws RentalItemNotExistError {
        HashMap<String, RentalItem> items = this.itemCollection.get(itemType);
        if (!items.containsKey(itemIdentifier)) {
            throw new RentalItemNotExistError("There is not such item in the library!");
        }
        return items.get(itemIdentifier);
    }

    /**
     * Get total item count
     * @param itemType Item type
     * @return Item count
     */
    public int getItemCount(RentalItemType itemType) {
        return this.itemCollectionCount.get(itemType);
    }

    /**
     * Get available item count from specific item type
     * @param itemType Item type
     */
    public int getAvailableItemCount(RentalItemType itemType) {
        return this.getAvailableItems(itemType).size();
    }

    /**
     * Checkout a item from the library
     * @param item Item to be checked out
     * @throws RentalItemNotExistError Item not exist error
     */
    public void checkout(RentalItem item) throws RentalItemNotExistError {
        String identifier = item.getDescription().getIdentifier();
        HashMap<String, RentalItem> items = this.itemCollection.get(item.getType());
        if (!items.containsKey(identifier)) {
            throw new RentalItemNotExistError("The item does not exist in the library!");
        }
        RentalItem itemInLibrary = items.get(identifier);
        if (itemInLibrary.getStatus().equals(RentalItemStatus.CHECKED_OUT)) {
            throw new RentalItemNotExistError("You cannot check out this item as it is already been checked out.");
        }
        itemInLibrary.setStatus(RentalItemStatus.CHECKED_OUT);
    }

    public void returnItem(RentalItem item) throws RentalItemNotExistError, RentalItemAlreadyExistError {
        String identifier = item.getDescription().getIdentifier();
        HashMap<String, RentalItem> items = this.itemCollection.get(item.getType());
        if (!items.containsKey(identifier)) {
            throw new RentalItemNotExistError("There is no record of this item in the library!");
        }
        RentalItem itemInLibrary = items.get(identifier);
        if (itemInLibrary.getStatus().equals(RentalItemStatus.IN_LIBRARY)) {
            throw new BookAlreadyExistError("The item is in the library!");
        }
        itemInLibrary.setStatus(RentalItemStatus.IN_LIBRARY);
    }

    public void returnBook(Book book) throws BookNotExistError, BookAlreadyExistError {
        String identifier = book.getDescription().getIdentifier();
        if (!this.bookCollection.containsKey(identifier)) {
            throw new BookNotExistError("There is no record of this book in the library!");
        }
        Book bookInLibrary = this.bookCollection.get(identifier);
        if (bookInLibrary.getStatus().equals(RentalItemStatus.IN_LIBRARY)) {
            throw new BookAlreadyExistError("The book is in librabry!");
        }
        bookInLibrary.setStatus(RentalItemStatus.IN_LIBRARY);
    }

    public Book getBookByIdentifier(String identifier) throws BookNotExistError {
        if (!this.bookCollection.containsKey(identifier)) {
            throw new BookNotExistError("There is no record of this book in the library!");
        }
        return this.bookCollection.get(identifier);
    }

    public int getAvailableBookCount() {
        return this.getAvailableBooks().size();
    }

    public Collection<Book> getAvailableBooks() {
        return this.bookCollection
                .values()
                .stream()
                .filter(book -> book.getStatus().equals(RentalItemStatus.IN_LIBRARY))
                .collect(Collectors.toList());
    }

    public String getName() {
        return this.name;
    }
}

