/*
 * Class: CMSC203
 * Instructor: Grinberg, Grigoriy A
 * Description: JavaFX GUI for the Property Management Application. Provides text
 *              fields for entering property data, buttons to add properties and
 *              view totals, and a status label that gives real-time feedback.
 * Due: 03/30/2026
 * Platform/compiler: Windows / Eclipse JDK 21 / JavaFX SDK 21
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 *    Print your Name here: Kamel Tchantchampo
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PropertyAppFX extends Application {

    /* The company object that holds all properties - same logic as the driver */
    private ManagementCompany company = new ManagementCompany("Campus Realty", "123-45-6789");

    /* Input fields */
    private TextField tf_name  = new TextField();
    private TextField tf_city  = new TextField();
    private TextField tf_rent  = new TextField();
    private TextField tf_owner = new TextField();

    /* Output area and status label */
    private TextArea  ta_output     = new TextArea();
    private Label     lbl_status    = new Label("Ready.");

    @Override
    public void start(Stage primary_stage) {

        // --- Title bar ---
        Label lbl_title = new Label("Property Management Application");
        lbl_title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        lbl_title.setAlignment(Pos.CENTER);

        // --- Input grid ---
        GridPane input_grid = new GridPane();
        input_grid.setHgap(10);
        input_grid.setVgap(8);
        input_grid.setPadding(new Insets(10));

        tf_name.setPromptText("e.g. Sunset Villa");
        tf_city.setPromptText("e.g. Rockville");
        tf_rent.setPromptText("e.g. 1500.00");
        tf_owner.setPromptText("e.g. Alice Johnson");

        input_grid.add(new Label("Property Name:"), 0, 0);
        input_grid.add(tf_name,  1, 0);
        input_grid.add(new Label("City:"),          0, 1);
        input_grid.add(tf_city,  1, 1);
        input_grid.add(new Label("Monthly Rent:"), 0, 2);
        input_grid.add(tf_rent,  1, 2);
        input_grid.add(new Label("Owner:"),         0, 3);
        input_grid.add(tf_owner, 1, 3);

        // --- Buttons ---
        Button btn_add        = new Button("Add Property");
        Button btn_show_all   = new Button("Show All Properties");
        Button btn_total_rent = new Button("Show Total Rent");
        Button btn_clear      = new Button("Clear Output");

        HBox btn_row = new HBox(10, btn_add, btn_show_all, btn_total_rent, btn_clear);
        btn_row.setPadding(new Insets(5, 10, 5, 10));

        // --- Output area ---
        ta_output.setEditable(false);
        ta_output.setPrefHeight(180);
        ta_output.setWrapText(true);
        ta_output.setFont(Font.font("Courier New", 12));

        // --- Status label at the bottom ---
        lbl_status.setTextFill(Color.DARKBLUE);

        // --- Root layout ---
        VBox root = new VBox(10,
                lbl_title,
                new Separator(),
                input_grid,
                btn_row,
                ta_output,
                lbl_status);
        root.setPadding(new Insets(12));

        // --- Button handlers ---
        btn_add.setOnAction(e        -> handleAddProperty());
        btn_show_all.setOnAction(e   -> handleShowAll());
        btn_total_rent.setOnAction(e -> handleTotalRent());
        btn_clear.setOnAction(e -> {
            ta_output.clear();
            lbl_status.setText("Output cleared.");
        });

        primary_stage.setTitle("CMSC203 - Property Management App");
        primary_stage.setScene(new Scene(root, 520, 480));
        primary_stage.setResizable(false);
        primary_stage.show();
    }

    /*
     * handleAddProperty - reads all four fields, validates them,
     * then adds the property to the company. Uses try/catch to handle
     * a non-numeric rent entry gracefully instead of crashing.
     */
    private void handleAddProperty() {
        String prop_name  = tf_name.getText().trim();
        String prop_city  = tf_city.getText().trim();
        String prop_owner = tf_owner.getText().trim();
        String rent_text  = tf_rent.getText().trim();

        // Check for empty fields before trying to parse
        if (prop_name.isEmpty() || prop_city.isEmpty()
                || prop_owner.isEmpty() || rent_text.isEmpty()) {
            lbl_status.setText("Please fill in all four fields.");
            lbl_status.setTextFill(Color.RED);
            return;
        }

        double prop_rent;
        try {
            prop_rent = Double.parseDouble(rent_text);
            if (prop_rent < 0) {
                throw new IllegalArgumentException("Rent must be a positive number.");
            }
        } catch (NumberFormatException ex) {
            lbl_status.setText("Rent must be a valid number (e.g. 1500.00).");
            lbl_status.setTextFill(Color.RED);
            return;
        } catch (IllegalArgumentException ex) {
            lbl_status.setText(ex.getMessage());
            lbl_status.setTextFill(Color.RED);
            return;
        }

        Property new_property = new Property(prop_name, prop_city, prop_rent, prop_owner);
        int slot_index = company.addProperty(new_property);

        if (slot_index == -1) {
            lbl_status.setText("Cannot add -- company is at max capacity ("
                    + ManagementCompany.MAX_PROPERTIES + " properties).");
            lbl_status.setTextFill(Color.RED);
        } else {
            ta_output.appendText("Added at index " + slot_index + ": "
                    + new_property.toString() + "\n");
            lbl_status.setText("Property added. Total properties: " + company.getPropertyCount()
                    + " / " + ManagementCompany.MAX_PROPERTIES);
            lbl_status.setTextFill(Color.DARKGREEN);
            clearFields();
        }
    }

    /*
     * handleShowAll - displays the full company toString() in the output area
     */
    private void handleShowAll() {
        ta_output.setText(company.toString());
        lbl_status.setText("Showing all properties.");
        lbl_status.setTextFill(Color.DARKBLUE);
    }

    /*
     * handleTotalRent - calculates and displays the total rent
     */
    private void handleTotalRent() {
        double total = company.totalRent();
        ta_output.setText("Total Monthly Rent: $" + String.format("%.2f", total));
        lbl_status.setText("Total rent calculated.");
        lbl_status.setTextFill(Color.DARKBLUE);
    }

    /* Clears all four input text fields */
    private void clearFields() {
        tf_name.clear();
        tf_city.clear();
        tf_rent.clear();
        tf_owner.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
