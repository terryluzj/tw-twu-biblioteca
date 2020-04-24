package com.twu.biblioteca.components.item;

/**
 * Rental item description interface
 */
interface RentalItemDescriptorInterface {
    String getIdentifier();
    String toString();
}

/**
 * Rental item descriptor
 */
public abstract class RentalItemDescriptor
    implements RentalItemDescriptorInterface {
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
