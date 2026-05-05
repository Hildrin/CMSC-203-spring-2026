import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketingofficejTest {

    private TicketingOffice office;

    @BeforeEach
    public void setUp() {
        office = new TicketingOffice("Blue", 30.0, 20.0, "images/office.jpg");
    }

    @Test
    public void testGetColor() {
        assertEquals("Blue", office.getColor());
    }

    @Test
    public void testGetLength() {
        assertEquals(12, office.getLength());
    }

    @Test
    public void testGetWidth() {
        assertEquals(30, office.getWidth());
    }

    @Test
    public void testGetBuildingType() {
        assertEquals("Ticketing Office", office.getBuildingType());
    }

    @Test
    public void testGetImagePath() {
        assertEquals("images/office.jpg", office.getImagePath());
    }

    @Test
    public void testSetColor() {
        office.setColor("Green");
        assertEquals("Green", office.getColor());
    }

    @Test
    public void testSetSize() {
        office.setSize(40, 23);
        assertEquals(40, office.getLength());
        assertEquals(23, office.getWidth());
    }
}