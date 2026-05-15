/*
 * Class: CMSC203
 * Instructor: Grinberg, Grigoriy A
 * Description: Manages a fixed-size array of Property objects for a real-estate
 *              company. Handles adding properties (with a deep copy), calculating
 *              total rent, and cloning the whole company via a copy constructor.
 * Due: 03/30/2026
 * Platform/compiler: Windows / Eclipse JDK 21
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Kamel Tchantchampo
 */

public class ManagementCompany {

    /* Max number of properties the company can hold */
    public static final int MAX_PROPERTIES = 5;

    private String     company_name;
    private String     tax_id;
    private Property[] property_list;
    private int        property_count;

    /*
     * Parameterized constructor - sets up the company with a name and tax ID,
     * then initializes the property array and counter to empty/zero
     */
    public ManagementCompany(String company_name, String tax_id) {
        this.company_name   = company_name;
        this.tax_id         = tax_id;
        this.property_list  = new Property[MAX_PROPERTIES];
        this.property_count = 0;
    }

    /*
     * Copy constructor - does a deep copy of another ManagementCompany.
     * Each Property in the original is copied individually using Property's
     * own copy constructor so the two companies don't share any references.
     */
    public ManagementCompany(ManagementCompany other) {
        this.company_name   = other.company_name;
        this.tax_id         = other.tax_id;
        this.property_count = other.property_count;
        this.property_list  = new Property[MAX_PROPERTIES];

        for (int i = 0; i < property_count; i++) {
            this.property_list[i] = new Property(other.property_list[i]);
        }
    }

    /*
     * addProperty - stores a deep copy of the given Property in the array.
     * Returns the index it was placed at, or -1 when the array is already full.
     */
    public int addProperty(Property p) {
        if (property_count >= MAX_PROPERTIES) {
            return -1;
        }
        property_list[property_count] = new Property(p);
        return property_count++;
    }

    /*
     * totalRent - loops through all stored properties and sums their rent amounts.
     * Wrapped in try/catch in case a slot somehow holds a bad reference.
     */
    public double totalRent() {
        double running_total = 0.0;
        try {
            for (int i = 0; i < property_count; i++) {
                running_total += property_list[i].getRentAmount();
            }
        } catch (NullPointerException ex) {
            System.out.println("totalRent error: null property found at index -- " + ex.getMessage());
        }
        return running_total;
    }

    /*
     * getPropertyCount - returns how many properties are currently stored
     */
    public int getPropertyCount() {
        return property_count;
    }

    /*
     * getProperty - returns the Property at the given index.
     * Returns null and prints a message if the index is out of range.
     */
    public Property getProperty(int index) {
        try {
            if (index < 0 || index >= property_count) {
                throw new ArrayIndexOutOfBoundsException(
                    "Index " + index + " is out of range (count=" + property_count + ").");
            }
            return property_list[index];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("getProperty error: " + ex.getMessage());
            return null;
        }
    }

    // ---------- Getters ----------

    /* Returns the company name */
    public String getName() { return company_name; }

    /* Returns the company tax ID */
    public String getTaxId() { return tax_id; }

    /*
     * toString - builds a formatted summary of the company and all its properties.
     * Displays company info, a divider, each property on its own line,
     * another divider, then the total rent.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("List of the properties for ").append(company_name)
              .append(", taxId: ").append(tax_id).append("\n");
        result.append("______________________________________________________\n");

        for (int i = 0; i < property_count; i++) {
            result.append(property_list[i].toString()).append("\n");
        }

        result.append("______________________________________________________\n");
        result.append("Total Management Fee: ").append(totalRent());
        return result.toString();
    }
}
