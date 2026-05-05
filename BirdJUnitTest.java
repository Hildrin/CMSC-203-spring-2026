import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BirdJUnitTest {

    private Bird bird;

    @BeforeEach
    public void setUp() {
        bird = new Bird("Tweety", 2, "Canary", "Yellow", "images/bird.jpg");
    }

    @Test
    public void testGetName() {
        assertEquals("Tweety", bird.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(2, bird.getAge());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("Canary", bird.getSpecies());
    }

    @Test
    public void testGetColor() {
        assertEquals("Yellow", bird.getColor());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/bird.jpg", bird.getImagePath());
    }

    @Test
    public void testToString() {
        assertEquals("Bird [Name: Tweety, Age: 2, Species: Canary, Color: Yellow]",
                     bird.toString());
    }
}