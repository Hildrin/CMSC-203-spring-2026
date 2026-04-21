import java.io.*;
import java.util.Scanner;

/**
 * SalesAppDriver
 * Console-based driver. Asks the user for file names, reads sales data,
 * displays statistics, and writes a summary file.
 */
public class SalesAppDriver {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter input file name: ");
        String inputFile = keyboard.nextLine().trim();

        System.out.print("Enter output file name: ");
        String outputFile = keyboard.nextLine().trim();

        double[][] data;
        try {
            data = SalesFileIO.readSalesData(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found - " + inputFile);
            keyboard.close();
            return;
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number format in data file.");
            keyboard.close();
            return;
        }

        // Display summary
        System.out.println("\n--- Sales Summary ---");
        System.out.printf("Total sales:   %.2f%n", SalesDataUtility.getTotal(data));
        System.out.printf("Average sale:  %.2f%n", SalesDataUtility.getAverage(data));
        System.out.printf("Highest sale:  %.2f%n", SalesDataUtility.getHighestInArray(data));
        System.out.printf("Lowest sale:   %.2f%n%n", SalesDataUtility.getLowestInArray(data));

        for (int r = 0; r < data.length; r++)
            System.out.printf("Row %d total: %.2f%n", r, SalesDataUtility.getRowTotal(data, r));
        System.out.println();

        int maxCols = 0;
        for (double[] row : data)
            if (row.length > maxCols) maxCols = row.length;
        for (int c = 0; c < maxCols; c++)
            System.out.printf("Column %d total: %.2f%n", c, SalesDataUtility.getColumnTotal(data, c));

        // Write output file
        try {
            SalesFileIO.writeSummary(outputFile, data);
            System.out.println("\nSummary written to: " + outputFile);
        } catch (IOException e) {
            System.out.println("ERROR writing output file: " + e.getMessage());
        }

        keyboard.close();
    }
}
