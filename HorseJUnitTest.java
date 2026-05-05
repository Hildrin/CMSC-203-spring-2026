
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HorseJUnitTest {

    private Horse horse;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        horse = new Horse("Spirit", 4, "Mustang", "Brown", "images/horse.png");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    // ===== GETTER TESTS =====
    @Test
    public void testGetName() {
        assertEquals("Spirit", horse.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(4, horse.getAge());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("Mustang", horse.getSpecies());
    }

    @Test
    public void testGetColor() {
        assertEquals("Brown", horse.getColor());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/horse.png", horse.getImagePath());
    }

    // ===== toString TEST =====
    @Test
    public void testToString() {
        String expected = "Horse [Name: Spirit, Age: 4, Species: Mustang, Color: Brown]";
        assertEquals(expected, horse.toString());
    }

    // ===== makeSound TEST =====
    @Test
    public void testMakeSound() {
        horse.makeSound();
        assertTrue(outputStream.toString().contains("Neigh!"));
    }

    // ===== move TEST =====
    @Test
    public void testMove() {
        horse.move();
        assertTrue(outputStream.toString().contains("Gallops."));
    }
}
