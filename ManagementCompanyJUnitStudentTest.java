import static org.junit.Assert.*;
import org.junit.Test;

public class ManagementCompanyJUnitStudentTest {

    // Test 1 - addProperty returns correct index values
    @Test
    public void testAddPropertyReturnsIndex() {
        ManagementCompany mc = new ManagementCompany("Campus Realty", "123-45-6789");
        int i0 = mc.addProperty(new Property("Apt A", "Rockville", 1200.0, "Alice"));
        int i1 = mc.addProperty(new Property("Apt B", "Bethesda",  1500.0, "Bob"));
        assertEquals(0, i0);
        assertEquals(1, i1);
    }

    // Test 2 - getPropertyCount reflects the actual number added
    @Test
    public void testGetPropertyCount() {
        ManagementCompany mc = new ManagementCompany("Campus Realty", "123-45-6789");
        mc.addProperty(new Property("Apt A", "Rockville", 1200.0, "Alice"));
        mc.addProperty(new Property("Apt B", "Bethesda",  1500.0, "Bob"));
        mc.addProperty(new Property("Apt C", "Silver Spring", 1100.0, "Carol"));
        assertEquals(3, mc.getPropertyCount());
    }

    // Test 3 - totalRent sums all rent amounts correctly
    @Test
    public void testTotalRent() {
        ManagementCompany mc = new ManagementCompany("Campus Realty", "123-45-6789");
        mc.addProperty(new Property("Apt A", "Rockville", 1200.0, "Alice"));
        mc.addProperty(new Property("Apt B", "Bethesda",  1500.0, "Bob"));
        assertEquals(2700.0, mc.totalRent(), 0.001);
    }

    // Test 4 - addProperty returns -1 when array is full
    @Test
    public void testAddPropertyWhenFull() {
        ManagementCompany mc = new ManagementCompany("Campus Realty", "123-45-6789");
        for (int i = 0; i < ManagementCompany.MAX_PROPERTIES; i++) {
            mc.addProperty(new Property("P" + i, "City", 1000.0 + i, "Owner"));
        }
        int result = mc.addProperty(new Property("Extra", "City", 2000.0, "OwnerX"));
        assertEquals(-1, result);
    }

    // Test 5 - copy constructor copies name, taxId, count, and total rent
    @Test
    public void testCopyConstructorBasicFields() {
        ManagementCompany mc1 = new ManagementCompany("Campus Realty", "123-45-6789");
        mc1.addProperty(new Property("Apt A", "Rockville", 1200.0, "Alice"));
        mc1.addProperty(new Property("Apt B", "Bethesda",  1500.0, "Bob"));

        ManagementCompany mc2 = new ManagementCompany(mc1);

        assertEquals(mc1.getName(), mc2.getName());
        assertEquals(mc1.getTaxId(), mc2.getTaxId());
        assertEquals(mc1.getPropertyCount(), mc2.getPropertyCount());
        assertEquals(mc1.totalRent(), mc2.totalRent(), 0.001);
    }

    // Test 6 - copy constructor does a deep copy (properties are not same references)
    @Test
    public void testCopyConstructorDeepCopy() {
        ManagementCompany mc1 = new ManagementCompany("Campus Realty", "123-45-6789");
        mc1.addProperty(new Property("Apt A", "Rockville", 1200.0, "Alice"));

        ManagementCompany mc2 = new ManagementCompany(mc1);

        assertNotSame(mc1.getProperty(0), mc2.getProperty(0));
    }
}
