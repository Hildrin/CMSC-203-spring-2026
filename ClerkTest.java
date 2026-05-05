import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClerkTest {

    private Clerk clerk;

    @BeforeEach
    public void setUp() {
        clerk = new Clerk("John", 30, 5, "Ticket Seller", "images/clerk.jpg");
    }

    @Test
    public void testGetName() {
        assertEquals("John", clerk.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(30, clerk.getAge());
    }

    @Test
    public void testGetYearsWorked() {
        assertEquals(5, clerk.getYearsWorked());
    }

    @Test
    public void testGetJob() {
        assertEquals("Ticket Seller", clerk.getJob());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/clerk.jpg", clerk.getImagePath());
    }

    @Test
    public void testToString() {
        assertTrue(clerk.toString().startsWith("Clerk - "));
    }
}