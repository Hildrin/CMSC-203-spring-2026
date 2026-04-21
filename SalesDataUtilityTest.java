import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * SalesDataUtilityTest
 * JUnit tests for all static methods in SalesDataUtility.
 */
public class SalesDataUtilityTest {

    // Primary ragged test array (from starter file)
    private double[][] sample;

    // Single-element array for edge testing
    private double[][] single;

    // Uniform (non-ragged) array
    private double[][] uniform;

    @Before
    public void setUp() {
        sample = new double[][] {
            {100.0, 200.0, 300.0},
            {50.0, 75.0},
            {400.0}
        };

        single = new double[][] {
            {42.0}
        };

        uniform = new double[][] {
            {10.0, 20.0},
            {30.0, 40.0}
        };
    }

    // -------------------------------------------------------
    // getTotal
    // -------------------------------------------------------
    @Test
    public void testGetTotal_raggedArray() {
        assertEquals(1125.0, SalesDataUtility.getTotal(sample), 0.001);
    }

    @Test
    public void testGetTotal_singleElement() {
        assertEquals(42.0, SalesDataUtility.getTotal(single), 0.001);
    }

    @Test
    public void testGetTotal_uniformArray() {
        assertEquals(100.0, SalesDataUtility.getTotal(uniform), 0.001);
    }

    // -------------------------------------------------------
    // getAverage
    // -------------------------------------------------------
    @Test
    public void testGetAverage_raggedArray() {
        // 6 elements: (100+200+300+50+75+400)/6 = 187.5
        assertEquals(187.5, SalesDataUtility.getAverage(sample), 0.001);
    }

    @Test
    public void testGetAverage_singleElement() {
        assertEquals(42.0, SalesDataUtility.getAverage(single), 0.001);
    }

    @Test
    public void testGetAverage_uniformArray() {
        // (10+20+30+40)/4 = 25.0
        assertEquals(25.0, SalesDataUtility.getAverage(uniform), 0.001);
    }

    // -------------------------------------------------------
    // getRowTotal
    // -------------------------------------------------------
    @Test
    public void testGetRowTotal_row0() {
        assertEquals(600.0, SalesDataUtility.getRowTotal(sample, 0), 0.001);
    }

    @Test
    public void testGetRowTotal_row1() {
        assertEquals(125.0, SalesDataUtility.getRowTotal(sample, 1), 0.001);
    }

    @Test
    public void testGetRowTotal_singleElementRow() {
        assertEquals(400.0, SalesDataUtility.getRowTotal(sample, 2), 0.001);
    }

    // -------------------------------------------------------
    // getColumnTotal (ragged - skips rows without that column)
    // -------------------------------------------------------
    @Test
    public void testGetColumnTotal_col0() {
        // 100 + 50 + 400 = 550
        assertEquals(550.0, SalesDataUtility.getColumnTotal(sample, 0), 0.001);
    }

    @Test
    public void testGetColumnTotal_col1() {
        // 200 + 75 (row 2 has no col 1)
        assertEquals(275.0, SalesDataUtility.getColumnTotal(sample, 1), 0.001);
    }

    @Test
    public void testGetColumnTotal_col2() {
        // 300 only (rows 1 and 2 have no col 2)
        assertEquals(300.0, SalesDataUtility.getColumnTotal(sample, 2), 0.001);
    }

    // -------------------------------------------------------
    // getHighestInRow / getLowestInRow
    // -------------------------------------------------------
    @Test
    public void testGetHighestInRow_row0() {
        assertEquals(300.0, SalesDataUtility.getHighestInRow(sample, 0), 0.001);
    }

    @Test
    public void testGetLowestInRow_row0() {
        assertEquals(100.0, SalesDataUtility.getLowestInRow(sample, 0), 0.001);
    }

    @Test
    public void testGetHighestInRow_singleElementRow() {
        assertEquals(400.0, SalesDataUtility.getHighestInRow(sample, 2), 0.001);
    }

    @Test
    public void testGetLowestInRow_singleElementRow() {
        assertEquals(400.0, SalesDataUtility.getLowestInRow(sample, 2), 0.001);
    }

    // -------------------------------------------------------
    // getHighestInArray / getLowestInArray
    // -------------------------------------------------------
    @Test
    public void testGetHighestInArray_raggedArray() {
        assertEquals(400.0, SalesDataUtility.getHighestInArray(sample), 0.001);
    }

    @Test
    public void testGetLowestInArray_raggedArray() {
        assertEquals(50.0, SalesDataUtility.getLowestInArray(sample), 0.001);
    }

    @Test
    public void testGetHighestInArray_singleElement() {
        assertEquals(42.0, SalesDataUtility.getHighestInArray(single), 0.001);
    }

    @Test
    public void testGetLowestInArray_singleElement() {
        assertEquals(42.0, SalesDataUtility.getLowestInArray(single), 0.001);
    }

    @Test
    public void testGetHighestInArray_uniformArray() {
        assertEquals(40.0, SalesDataUtility.getHighestInArray(uniform), 0.001);
    }

    @Test
    public void testGetLowestInArray_uniformArray() {
        assertEquals(10.0, SalesDataUtility.getLowestInArray(uniform), 0.001);
    }
}
