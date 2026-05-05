import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArenaTest {

    private Arena arena;

    @BeforeEach
    public void setUp() {
        arena = new Arena("Red", 100.0, 50.0, "images/arena.jpg");
    }

    @Test
    public void testGetColor() {
        assertEquals("Red", arena.getColor());
    }

    @Test
    public void testGetLength() {
        assertEquals(10, arena.getLength());
    }

    @Test
    public void testGetWidth() {
        assertEquals(53, arena.getWidth());
    }

    @Test
    public void testGetBuildingType() {
        assertEquals("Arena", arena.getBuildingType());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/arena.jpg", arena.getImagePath());
    }

    @Test
    public void testSetColor() {
        arena.setColor("Blue");
        assertEquals("Blue", arena.getColor());
    }

    @Test
    public void testSetSize() {
        arena.setSize(300, 200.0);
        assertEquals(300.0, arena.getLength());
        assertEquals(200.0, arena.getWidth());
    }
}