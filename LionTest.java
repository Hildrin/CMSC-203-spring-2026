import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LionTest {

    private Lion lion;

    @BeforeEach
    public void setUp() {
        lion = new Lion("Simba", 5, "Feline", "Golden", "images/lion.jpg");
    }

    @Test
    public void testGetName() {
        assertEquals("Simba", lion.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(5, lion.getAge());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("Feline", lion.getSpecies());
    }

    @Test
    public void testGetColor() {
        assertEquals("Golden", lion.getColor());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/lion.jpg", lion.getImagePath());
    }

    @Test
    public void testToString() {
        assertEquals("Lion [Name: Simba, Age: 5, Species: Feline, Color: Golden]",
                     lion.toString());
    }
}