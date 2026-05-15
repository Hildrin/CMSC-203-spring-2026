import static org.junit.Assert.*;
import org.junit.Test;

public class PropertyJUnitStudentTest {

    // Test 1 - default constructor sets all fields to empty/zero
    @Test
    public void testDefaultConstructor() {
        Property p = new Property();
        assertEquals("", p.getPropertyName());
        assertEquals("", p.getCity());
        assertEquals(0.0, p.getRentAmount(), 0.001);
        assertEquals("", p.getOwner());
    }

    // Test 2 - parameterized constructor stores the correct values
    @Test
    public void testParameterizedConstructor() {
        Property p = new Property("Sunset Villa", "Rockville", 1500.0, "Alice");
        assertEquals("Sunset Villa", p.getPropertyName());
        assertEquals("Rockville", p.getCity());
        assertEquals(1500.0, p.getRentAmount(), 0.001);
        assertEquals("Alice", p.getOwner());
    }

    // Test 3 - copy constructor produces equal field values
    @Test
    public void testCopyConstructorValues() {
        Property original = new Property("Harbor View", "Bethesda", 2200.0, "Bob");
        Property copy = new Property(original);
        assertEquals(original.getPropertyName(), copy.getPropertyName());
        assertEquals(original.getCity(), copy.getCity());
        assertEquals(original.getRentAmount(), copy.getRentAmount(), 0.001);
        assertEquals(original.getOwner(), copy.getOwner());
    }

    // Test 4 - copy constructor creates an independent object (not the same reference)
    @Test
    public void testCopyConstructorIndependence() {
        Property original = new Property("Harbor View", "Bethesda", 2200.0, "Bob");
        Property copy = new Property(original);
        assertNotSame(original, copy);
        // Changing original should not affect the copy
        original.setRentAmount(9999.0);
        assertEquals(2200.0, copy.getRentAmount(), 0.001);
    }

    // Test 5 - setters update values correctly
    @Test
    public void testSetters() {
        Property p = new Property();
        p.setPropertyName("Blue Ridge");
        p.setCity("Gaithersburg");
        p.setRentAmount(1300.0);
        p.setOwner("Carol");
        assertEquals("Blue Ridge", p.getPropertyName());
        assertEquals("Gaithersburg", p.getCity());
        assertEquals(1300.0, p.getRentAmount(), 0.001);
        assertEquals("Carol", p.getOwner());
    }

    // Test 6 - toString returns the expected comma-separated format
    @Test
    public void testToString() {
        Property p = new Property("Green Meadows", "Silver Spring", 1800.0, "Dan");
        String result = p.toString();
        assertTrue(result.contains("Green Meadows"));
        assertTrue(result.contains("Silver Spring"));
        assertTrue(result.contains("Dan"));
        assertTrue(result.contains("1800.0"));
    }
}
