import java.io.*;
import java.util.*;

/**
 * SalesFileIO
 * Handles reading sales data from a text file and writing summary output.
 */
public class SalesFileIO {

    /**
     * Reads a ragged 2D array from a text file.
     * Each non-blank line represents a row; values are space-separated doubles.
     *
     * @param filename path to the input file
     * @return ragged 2D double array
     * @throws FileNotFoundException if the file does not exist
     */
    public static double[][] readSalesData(String filename) throws FileNotFoundException {
        List<double[]> rows = new ArrayList<>();
        Scanner fileScanner = new Scanner(new File(filename));

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            double[] rowData = new double[tokens.length];
            for (int i = 0; i < tokens.length; i++)
                rowData[i] = Double.parseDouble(tokens[i]);
            rows.add(rowData);
        }
        fileScanner.close();
        return rows.toArray(new double[0][]);
    }

    /**
     * Writes a formatted sales summary to an output file.
     *
     * @param filename path to the output file
     * @param data     the ragged 2D sales array
     * @throws IOException if the file cannot be written
     */
    public static void writeSummary(String filename, double[][] data) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filename));

        writer.printf("Total sales: %.2f%n", SalesDataUtility.getTotal(data));
        writer.printf("Average sale: %.2f%n", SalesDataUtility.getAverage(data));
        writer.printf("Highest sale: %.2f%n", SalesDataUtility.getHighestInArray(data));
        writer.printf("Lowest sale: %.2f%n%n", SalesDataUtility.getLowestInArray(data));

        // Row totals
        for (int r = 0; r < data.length; r++)
            writer.printf("Row %d total: %.2f%n", r, SalesDataUtility.getRowTotal(data, r));
        writer.println();

        // Column totals (up to max columns present)
        int maxCols = 0;
        for (double[] row : data)
            if (row.length > maxCols) maxCols = row.length;
        for (int c = 0; c < maxCols; c++)
            writer.printf("Column %d total: %.2f%n", c, SalesDataUtility.getColumnTotal(data, c));
        writer.println();

        // Highest/lowest per row
        for (int r = 0; r < data.length; r++) {
            writer.printf("Row %d highest: %.2f  lowest: %.2f%n",
                    r,
                    SalesDataUtility.getHighestInRow(data, r),
                    SalesDataUtility.getLowestInRow(data, r));
        }

        writer.close();
    }
}
