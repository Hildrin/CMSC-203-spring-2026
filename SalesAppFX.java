import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * SalesAppFX
 * JavaFX GUI for the Store Sales Data Processor.
 *
 * Layout:
 *   - Input file field + Browse button
 *   - Output file field
 *   - "Load and Process" button  |  "Save Summary" button
 *   - TextArea showing results
 */
public class SalesAppFX extends Application {

    private TextField tfInputFile;
    private TextField tfOutputFile;
    private TextArea  taOutput;
    private double[][] currentData;

    @Override
    public void start(Stage primaryStage) {

        // --- Input file row ---
        tfInputFile = new TextField("salesdata.txt");
        tfInputFile.setPrefWidth(300);
        Button btnBrowse = new Button("Browse...");
        btnBrowse.setOnAction(e -> handleBrowse(primaryStage));

        HBox inputRow = new HBox(8, new Label("Input File:"), tfInputFile, btnBrowse);
        inputRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        // --- Output file row ---
        tfOutputFile = new TextField("sales_summary.txt");
        tfOutputFile.setPrefWidth(300);

        HBox outputRow = new HBox(8, new Label("Output File:"), tfOutputFile);
        outputRow.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        // --- Buttons ---
        Button btnLoad = new Button("Load & Process");
        Button btnSave = new Button("Save Summary");
        Button btnClear = new Button("Clear");

        btnLoad.setOnAction(e -> handleLoad());
        btnSave.setOnAction(e -> handleSave());
        btnClear.setOnAction(e -> { taOutput.clear(); currentData = null; });

        HBox buttons = new HBox(10, btnLoad, btnSave, btnClear);

        // --- Output area ---
        taOutput = new TextArea();
        taOutput.setEditable(false);
        taOutput.setWrapText(true);
        taOutput.setPrefHeight(280);

        // --- Layout ---
        VBox root = new VBox(10, inputRow, outputRow, buttons,
                new Separator(), taOutput);
        root.setPadding(new Insets(15));

        primaryStage.setTitle("Store Sales Data Processor");
        primaryStage.setScene(new Scene(root, 640, 450));
        primaryStage.show();
    }

    /** Opens a FileChooser so the user can pick a .txt input file. */
    private void handleBrowse(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select Sales Data File");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File chosen = fc.showOpenDialog(stage);
        if (chosen != null)
            tfInputFile.setText(chosen.getAbsolutePath());
    }

    /** Loads the input file, computes all statistics, and populates the TextArea. */
    private void handleLoad() {
        String inFile = tfInputFile.getText().trim();
        taOutput.clear();
        try {
            currentData = SalesFileIO.readSalesData(inFile);
            taOutput.appendText("Data loaded from: " + inFile + "\n\n");

            double total   = SalesDataUtility.getTotal(currentData);
            double avg     = SalesDataUtility.getAverage(currentData);
            double highest = SalesDataUtility.getHighestInArray(currentData);
            double lowest  = SalesDataUtility.getLowestInArray(currentData);

            taOutput.appendText(String.format("Total sales:  %.2f%n", total));
            taOutput.appendText(String.format("Average sale: %.2f%n", avg));
            taOutput.appendText(String.format("Highest sale: %.2f%n", highest));
            taOutput.appendText(String.format("Lowest sale:  %.2f%n%n", lowest));

            // Row totals and per-row high/low
            taOutput.appendText("--- Row Totals ---\n");
            for (int r = 0; r < currentData.length; r++) {
                taOutput.appendText(String.format(
                        "Row %d: total=%.2f  high=%.2f  low=%.2f%n",
                        r,
                        SalesDataUtility.getRowTotal(currentData, r),
                        SalesDataUtility.getHighestInRow(currentData, r),
                        SalesDataUtility.getLowestInRow(currentData, r)));
            }

            // Column totals
            taOutput.appendText("\n--- Column Totals ---\n");
            int maxCols = 0;
            for (double[] row : currentData)
                if (row.length > maxCols) maxCols = row.length;
            for (int c = 0; c < maxCols; c++) {
                taOutput.appendText(String.format(
                        "Column %d: total=%.2f%n",
                        c, SalesDataUtility.getColumnTotal(currentData, c)));
            }

        } catch (FileNotFoundException ex) {
            taOutput.appendText("ERROR: Input file not found - " + inFile + "\n");
        } catch (NumberFormatException ex) {
            taOutput.appendText("ERROR: Invalid number in data file.\n");
        }
    }

    /** Writes the summary to the specified output file. */
    private void handleSave() {
        if (currentData == null) {
            taOutput.appendText("No data loaded. Click \"Load & Process\" first.\n");
            return;
        }
        String outFile = tfOutputFile.getText().trim();
        try {
            SalesFileIO.writeSummary(outFile, currentData);
            taOutput.appendText("\nSummary written to: " + outFile + "\n");
        } catch (IOException ex) {
            taOutput.appendText("ERROR writing summary: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
