/**
 * SalesDataUtility
 * Static utility methods for processing ragged 2D sales arrays.
 */
public class SalesDataUtility {

    /** Returns the sum of all values in the ragged array. */
    public static double getTotal(double[][] data) {
        double total = 0;
        for (double[] row : data)
            for (double val : row)
                total += val;
        return total;
    }

    /** Returns the average of all values in the ragged array. */
    public static double getAverage(double[][] data) {
        int count = 0;
        for (double[] row : data)
            count += row.length;
        if (count == 0) return 0;
        return getTotal(data) / count;
    }

    /** Returns the total of a single row. */
    public static double getRowTotal(double[][] data, int row) {
        double total = 0;
        for (double val : data[row])
            total += val;
        return total;
    }

    /**
     * Returns the total of a column across all rows.
     * Skips rows that do not have that column index (ragged handling).
     */
    public static double getColumnTotal(double[][] data, int col) {
        double total = 0;
        for (double[] row : data)
            if (col < row.length)
                total += row[col];
        return total;
    }

    /** Returns the highest value in a single row. */
    public static double getHighestInRow(double[][] data, int row) {
        double highest = data[row][0];
        for (double val : data[row])
            if (val > highest) highest = val;
        return highest;
    }

    /** Returns the lowest value in a single row. */
    public static double getLowestInRow(double[][] data, int row) {
        double lowest = data[row][0];
        for (double val : data[row])
            if (val < lowest) lowest = val;
        return lowest;
    }

    /** Returns the highest value across the entire ragged array. */
    public static double getHighestInArray(double[][] data) {
        double highest = data[0][0];
        for (double[] row : data)
            for (double val : row)
                if (val > highest) highest = val;
        return highest;
    }

    /** Returns the lowest value across the entire ragged array. */
    public static double getLowestInArray(double[][] data) {
        double lowest = data[0][0];
        for (double[] row : data)
            for (double val : row)
                if (val < lowest) lowest = val;
        return lowest;
    }
}
