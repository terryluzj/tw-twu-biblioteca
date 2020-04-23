package com.twu.biblioteca.component;

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

    private String name;
    private RentalItemDescriptor description;

    abstract RentalItemDescriptor getDescription();

    public String getName() {
        return this.name;
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