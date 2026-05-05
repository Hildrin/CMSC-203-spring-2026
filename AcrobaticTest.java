import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AcrobaticTest {

    private Acrobatic acrobatic;

    @BeforeEach
    public void setUp() {
        acrobatic = new Acrobatic("Sara", 25, 3, "Acrobat", "images/acrobat.jpg");
    }

    @Test
    public void testGetName() {
        assertEquals("Sara", acrobatic.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(25, acrobatic.getAge());
    }

    @Test
    public void testGetYearsWorked() {
        assertEquals(3, acrobatic.getYearsWorked());
    }

    @Test
    public void testGetJob() {
        assertEquals("Acrobat", acrobatic.getJob());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/acrobat.jpg", acrobatic.getImagePath());
    }

    @Test
    public void testToString() {
        assertTrue(acrobatic.toString().startsWith("Acrobatic - "));
    }
}