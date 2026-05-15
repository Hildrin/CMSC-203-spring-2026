/*
 * Class: CMSC203
 * Instructor: Grinberg, Grigoriy A
 * Description: Console driver for the Property Management Application.
 *              Creates a ManagementCompany, adds several Property objects,
 *              and demonstrates aggregation, arrays of objects, and copy constructors.
 * Due: 03/30/2026
 * Platform/compiler: Windows / Eclipse JDK 21
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Kamel Tchantchampo
 */

public class PropertyDriver {

    public static void main(String[] args) {

        // --- Set up the company ---
        ManagementCompany company = new ManagementCompany("Campus Realty", "123-45-6789");

        // --- Build properties using the parameterized constructor ---
        Property p1 = new Property("Sunset Villa",   "Rockville",    1500.00, "Alice Johnson");
        Property p2 = new Property("Harbor View",    "Bethesda",     2200.00, "Bob Martinez");
        Property p3 = new Property("Green Meadows",  "Silver Spring", 1800.00, "Carol Smith");
        Property p4 = new Property("Blue Ridge",     "Gaithersburg", 1300.00, "Dan Lee");

        // --- Add each property; addProperty returns the index or -1 if full ---
        try {
            int idx1 = company.addProperty(p1);
            int idx2 = company.addProperty(p2);
            int idx3 = company.addProperty(p3);
            int idx4 = company.addProperty(p4);

            System.out.println("Added p1 at index: " + idx1);
            System.out.println("Added p2 at index: " + idx2);
            System.out.println("Added p3 at index: " + idx3);
            System.out.println("Added p4 at index: " + idx4);

        } catch (Exception ex) {
            System.out.println("Error adding property: " + ex.getMessage());
        }

        // --- Print the full company summary ---
        System.out.println("\n" + company.toString());

        // --- Demonstrate Property copy constructor ---
        System.out.println("\n--- Property Copy Constructor Demo ---");
        Property original_prop  = new Property("Oak Terrace", "Laurel", 1650.00, "Emma Ray");
        Property copied_prop    = new Property(original_prop);
        System.out.println("Original : " + original_prop);
        System.out.println("Copy     : " + copied_prop);

        // Mutate original to prove the copy is independent
        original_prop.setRentAmount(9999.00);
        System.out.println("After changing original rent to 9999:");
        System.out.println("Original : " + original_prop);
        System.out.println("Copy     : " + copied_prop);  // should still show 1650.0

        // --- Demonstrate ManagementCompany deep copy constructor ---
        System.out.println("\n--- ManagementCompany Deep Copy Demo ---");
        ManagementCompany company_copy = new ManagementCompany(company);
        System.out.println("Original total rent : $" + company.totalRent());
        System.out.println("Copied   total rent : $" + company_copy.totalRent());
        System.out.println("Same property reference? "
            + (company.getProperty(0) == company_copy.getProperty(0)));  // should be false
    }
}
