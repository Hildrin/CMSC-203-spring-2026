/*
 * Class: CMSC203
 * Instructor: Grinberg, Grigoriy A
 * Description: Represents a single rental property. Stores the property name,
 *              city, monthly rent amount, and owner name. Supports a default
 *              constructor, a parameterized constructor, and a copy constructor.
 * Due: 03/30/2026
 * Platform/compiler: Windows / Eclipse JDK 21
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Kamel Tchantchampo
 */

public class Property {

    private String property_name;
    private String city;
    private double rent_amount;
    private String owner;

    /*
     * Default constructor - initializes all fields to empty/zero defaults
     * so the object is always in a valid state right after creation
     */
    public Property() {
        property_name = "";
        city          = "";
        rent_amount   = 0.0;
        owner         = "";
    }

    /*
     * Parameterized constructor - builds a Property from the four
     * values passed in by the caller
     */
    public Property(String property_name, String city,
                    double rent_amount, String owner) {
        this.property_name = property_name;
        this.city          = city;
        this.rent_amount   = rent_amount;
        this.owner         = owner;
    }

    /*
     * Copy constructor - takes an existing Property and makes an
     * independent copy of every field so changes to one don't affect the other
     */
    public Property(Property other) {
        property_name = other.property_name;
        city          = other.city;
        rent_amount   = other.rent_amount;
        owner         = other.owner;
    }

    // ---------- Getters ----------

    /* Returns the name of the property */
    public String getPropertyName() { return property_name; }

    /* Returns the city the property is located in */
    public String getCity() { return city; }

    /* Returns the monthly rent amount */
    public double getRentAmount() { return rent_amount; }

    /* Returns the name of the property owner */
    public String getOwner() { return owner; }

    // ---------- Setters ----------

    /* Sets the property name */
    public void setPropertyName(String property_name) {
        this.property_name = property_name;
    }

    /* Sets the city */
    public void setCity(String city) {
        this.city = city;
    }

    /*
     * Sets the rent amount - wrapped in a try/catch so a bad value
     * (like a negative number passed as a string somewhere upstream)
     * doesn't crash the program; instead rent stays at its current value
     */
    public void setRentAmount(double rent_amount) {
        try {
            if (rent_amount < 0) {
                throw new IllegalArgumentException("Rent cannot be negative.");
            }
            this.rent_amount = rent_amount;
        } catch (IllegalArgumentException ex) {
            System.out.println("setRentAmount error: " + ex.getMessage()
                + " -- keeping previous value: " + this.rent_amount);
        }
    }

    /* Sets the owner name */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /*
     * Returns a comma-separated string showing all four fields.
     * Format matches what the JUnit test expects:
     * propertyName,city,owner,rentAmount  (e.g. "Green Meadows,Silver Spring,Dan,1800.0")
     */
    @Override
    public String toString() {
        return property_name + "," + city + "," + owner + "," + rent_amount;
    }
}
