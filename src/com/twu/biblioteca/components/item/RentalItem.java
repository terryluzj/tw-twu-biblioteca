package com.twu.biblioteca.components.item;

/**
 * Rental item interface
 */
interface RentalItemInterface {
    String getName();
}

/**
 * Rental item abstract class
 */
public abstract class RentalItem implements RentalItemInterface {
    private final String name;
    private final RentalItemType type;
    private RentalItemDescriptor description;
    private RentalItemStatus itemStatus = RentalItemStatus.IN_LIBRARY;

    /**
     * Abstract rental item
     * @param name Item name
     * @param type Item enum type
     */
    public RentalItem(String name, RentalItemType type) {
        this.name = name;
        this.type = type;
    }

    public RentalItemDescriptor getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public RentalItemStatus getStatus() {
        return this.itemStatus;
    }

    public void setStatus(RentalItemStatus status) {
        this.itemStatus = status;
    }

    public RentalItemType getType() {
        return this.type;
    }
}
