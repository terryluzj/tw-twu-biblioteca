package com.twu.biblioteca.component.item;

/**
 * Rental item interface
 */
interface RentalItemInterface {
    String getName();
}

/**
 * Rental item description interface
 */
interface RentalItemDescriptorInterface {
    String getIdentifier();
    String toString();
}

/**
 * Rental item abstract class
 */
abstract class RentalItem implements RentalItemInterface {

    private final String name;
    private final String type;
    private RentalItemDescriptor description;
    private RentalItemStatus itemStatus = RentalItemStatus.IN_LIBRARY;

    abstract RentalItemDescriptor getDescription();

    public RentalItem(String name, String type) {
        this.name = name;
        this.type = type;
    };

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public RentalItemStatus getStatus() {
        return this.itemStatus;
    }

    public void setStatus(RentalItemStatus status) {
        this.itemStatus = status;
    }
}

/**
 * Rental item descriptor
 */
abstract class RentalItemDescriptor implements RentalItemDescriptorInterface {

    private final String name;

    public RentalItemDescriptor(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.name;
    }
}