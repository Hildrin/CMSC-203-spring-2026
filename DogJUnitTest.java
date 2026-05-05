
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DogJUnitTest {

    private Dog dog;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        dog = new Dog("Buddy", 3, "Canine", "Black", "images/dog.png");
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    @Test
    public void testGetName() {
        assertEquals("Buddy", dog.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(3, dog.getAge());
    }

    @Test
    public void testGetSpecies() {
        assertEquals("Canine", dog.getSpecies());
    }

    @Test
    public void testGetColor() {
        assertEquals("Black", dog.getColor());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/dog.png", dog.getImagePath());
    }

    @Test
    public void testToString() {
        String expected =
            "Dog [Name: Buddy, Age: 3, Species: Canine, Color: Black]";
        assertEquals(expected, dog.toString());
    }

    // ===== makeSound TEST (portable) =====
    @Test
    public void testMakeSound() {
        dog.makeSound();
        assertEquals("Bark!" + System.lineSeparator(),
                     outputStream.toString());
    }

    // ===== move TEST (portable, matches extra newline) =====
    @Test
    public void testMove() {
        dog.move();

        String output = outputStream.toString()
                                    .replace("\r\n", "\n");

        assertEquals("Walks around.\n\n", output);
    }
}
