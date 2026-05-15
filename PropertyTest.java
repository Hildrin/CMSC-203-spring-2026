import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PropertyTest.java
 *
 * JUnit 5 example test class for the Property class.
 * Demonstrates the basic structure of a JUnit 5 test file:
 *   - One instance variable (the object under test)
 *   - A @BeforeEach setup method that runs before every test
 *   - Three @Test methods, one per behaviour being verified
 *
 * Property constructor used:
 *   Property(String propertyName, String city, double rentAmount, String owner)
 */
public class PropertyTest {

    // ---------------------------------------------------------------
    // Instance variable – shared across all test methods
    // ---------------------------------------------------------------
    private Property property;

    // ---------------------------------------------------------------
    // @BeforeEach – runs once before EACH test method
    // Creates a fresh Property object so tests are independent
    // ---------------------------------------------------------------
    @BeforeEach
    public void setUp() {
        property = new Property("Sunset Villa", "Rockville", 1500.00, "Alice Smith");
    }

    // ---------------------------------------------------------------
    // Test 1 – verify getPropertyName() returns the correct value
    // ---------------------------------------------------------------
    @Test
    public void testGetPropertyName() {
        assertEquals("Sunset Villa", property.getPropertyName());
    }

    // ---------------------------------------------------------------
    // Test 2 – verify getRentAmount() returns the correct value
    // ---------------------------------------------------------------
    @Test
    public void testGetRentAmount() {
        assertEquals(1500.00, property.getRentAmount(), 0.001);
    }

    // ---------------------------------------------------------------
    // Test 3 – verify toString() returns the expected formatted string
    // ---------------------------------------------------------------
    @Test
    public void testToString() {
        String expected = "Sunset Villa, Rockville, Rent: $1500.00, Owner: Alice Smith";
        assertEquals(expected, property.toString());
    }
}
