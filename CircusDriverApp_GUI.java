import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class CircusDriverApp_GUI extends Application {

    private Circus circus = new Circus();
    private VBox animalListBox;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("🎪 Bob's Circus Management System");

        // ================= OUTPUT AREA =================
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(770);

        // ================= MENU BUTTONS =================
        Button addAnimalBtn = new Button("Add Animal");
        Button addPersonBtn = new Button("Add Person");
        Button addBuildingBtn = new Button("Add Building");
        Button ticketBtn = new Button("Generate Ticket");

        Button displayAnimalsBtn = new Button("Display All Animals");
        Button displayPersonsBtn = new Button("Display All Persons");
        Button displayBuildingsBtn = new Button("Display All Buildings");

        Button sortAgeBtn = new Button("Sort Animals by Age");
        Button sortNameBtn = new Button("Sort Animals by Name");
        Button searchBtn = new Button("Search Animal by Name");

        Button exitBtn = new Button("Exit");

        FlowPane menu = new FlowPane(10, 10,
                addAnimalBtn, addPersonBtn, addBuildingBtn, ticketBtn,
                displayAnimalsBtn, displayPersonsBtn, displayBuildingsBtn,
                sortAgeBtn, sortNameBtn, searchBtn, exitBtn
        );
        menu.setAlignment(Pos.CENTER);

        VBox leftPanel = new VBox(10, menu, outputArea);
        leftPanel.setPrefWidth(600);

        // ================= ANIMAL DISPLAY =================
        animalListBox = new VBox(8);
        animalListBox.setPadding(new Insets(10));

        ScrollPane animalScrollPane = new ScrollPane(animalListBox);
        animalScrollPane.setFitToWidth(true);
        animalScrollPane.setPrefHeight(800);

        VBox animalPanel = new VBox(8,
                new Label("Image + Details"),
                animalScrollPane
        );
        animalPanel.setPrefHeight(850);
        animalPanel.setPadding(new Insets(10));
        animalPanel.setStyle("-fx-border-color: gray;");

        // ================= ROOT =================
        HBox root = new HBox(15, leftPanel, animalPanel);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        // ================= ACTIONS =================
        addAnimalBtn.setOnAction(e -> handleAddAnimal());
        addPersonBtn.setOnAction(e -> handleAddPerson());
        addBuildingBtn.setOnAction(e -> handleAddBuilding());
        ticketBtn.setOnAction(e -> handleGenerateTicket());

        displayAnimalsBtn.setOnAction(e -> {
            displayAnimalsWithImages();
            outputArea.setText(captureConsole(circus::displayAllAnimals));
        });

        displayPersonsBtn.setOnAction(e -> {
            displayPersonsWithImages();
            outputArea.setText(captureConsole(circus::displayAllPersons));
        });

        displayBuildingsBtn.setOnAction(e -> {
            displayBuildingsWithImages();
            outputArea.setText(captureConsole(circus::displayAllBuildings));
        });

        sortAgeBtn.setOnAction(e -> circus.sortAnimalsByAge());
        sortNameBtn.setOnAction(e -> circus.sortAnimalsByName());
        searchBtn.setOnAction(e -> handleSearchAnimal());
        exitBtn.setOnAction(e -> stage.close());
    }

    // ==================== TICKET GENERATION ====================
    private void handleGenerateTicket() {

        animalListBox.getChildren().clear();

        StringBuilder allDetails = new StringBuilder();
        allDetails.append("Ticket Calculation Details:\n");
        allDetails.append("---------------------------\n");

        boolean moreTickets = true;

        while (moreTickets) {

            List<String> dayChoices = new ArrayList<>();
            for (DayOfWeek d : DayOfWeek.values()) {
                int pct = (int) (d.getDiscount() * 100);
                dayChoices.add(d.name().charAt(0) + d.name().substring(1).toLowerCase()
                        + " (" + pct + "% discount)");
            }

            ChoiceDialog<String> dayDialog = new ChoiceDialog<>(dayChoices.get(0), dayChoices);
            dayDialog.setHeaderText("Select Day of Week");
            Optional<String> dayOpt = dayDialog.showAndWait();
            if (dayOpt.isEmpty()) break;

            String selectedDay = dayOpt.get();
            String day = selectedDay.split(" ")[0];

            List<String> customerChoices = List.of(
                    "Child (10% discount)",
                    "Student (10% discount)",
                    "Adult",
                    "Senior (5% discount)"
            );

            ChoiceDialog<String> customerDialog = new ChoiceDialog<>(customerChoices.get(0), customerChoices);
            customerDialog.setHeaderText("Select Customer Type");
            Optional<String> customerOpt = customerDialog.showAndWait();
            if (customerOpt.isEmpty()) break;

            String customerTypeSelected = customerOpt.get();

            double customerDiscount = switch (customerTypeSelected) {
                case "Child (10% discount)", "Student (10% discount)" -> 0.10;
                case "Senior (5% discount)" -> 0.05;
                default -> 0.0;
            };

            List<String> seatChoices = List.of(
                    "Lower Level",
                    "T-Level",
                    "Upper Level"
            );

            ChoiceDialog<String> seatDialog = new ChoiceDialog<>(seatChoices.get(0), seatChoices);
            seatDialog.setHeaderText("Select Seat Level");
            Optional<String> seatOpt = seatDialog.showAndWait();
            if (seatOpt.isEmpty()) break;

            String seatLevel = seatOpt.get();

            double basePrice = inputDouble("Enter base price:");
            int ticketCount = inputInt("Enter number of tickets:");

            double singleTicketPrice = calculateTicketPrice(day, basePrice, seatLevel, customerDiscount);
            double totalPrice = singleTicketPrice * ticketCount;

            allDetails.append(getSingleTicketDetails(day, basePrice, seatLevel, customerDiscount));
            allDetails.append(String.format("Number of Tickets: %d%n", ticketCount));
            allDetails.append(String.format("Total Price: $%.2f%n", totalPrice));
            allDetails.append("---------------------------\n");

            Alert moreAlert = new Alert(Alert.AlertType.CONFIRMATION);
            moreAlert.setHeaderText("Do you want to buy more tickets?");
            moreAlert.setContentText("Click OK for Yes, Cancel for No");

            Optional<ButtonType> moreOpt = moreAlert.showAndWait();
            moreTickets = moreOpt.isPresent() && moreOpt.get() == ButtonType.OK;
        }

        allDetails.append("\nEnjoy the show!");
        outputArea.setText(allDetails.toString());
    }


    private void handleAddAnimal() {
        ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Dog", "Dog", "Horse", "Bird", "Lion");
        dialog.setHeaderText("Select Animal Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;

        String name = inputText("Enter name:");
        int age = inputInt("Enter age:");
        String species = inputText("Enter species:");
        String color = inputText("Enter color:");
        String imagePath = inputText("Enter image file path:");

        Animal animal = switch (type.get()) {
            case "Dog" -> new Dog(name, age, species, color, imagePath);
            case "Horse" -> new Horse(name, age, species, color, imagePath);
            case "Bird" -> new Bird(name, age, species, color, imagePath);
            case "Lion" -> new Lion(name, age, species, color, imagePath);
            default -> null;
        };

        if (animal != null) circus.addAnimal(animal);
    }

    private void handleAddPerson() {
    
        animalListBox.getChildren().clear();  // Clear animal display area
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Clerk", "Clerk", "Acrobatic");
        dialog.setHeaderText("Select Person Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;

        String name = inputText("Enter name:");
        int age = inputInt("Enter age:");
        int yearsWorked = inputInt("Enter years worked:");
        String job = inputText("Enter job description:");
        String imagePath = inputText("Enter image file path:");  

        Person person = switch (type.get()) {
            case "Clerk" -> new Clerk(name, age, yearsWorked, job, imagePath);
            case "Acrobatic" -> new Acrobatic(name, age, yearsWorked, job, imagePath);
            default -> null;
        };

        if (person != null) circus.addPerson(person);
    }

    private void handleAddBuilding() {
        
        animalListBox.getChildren().clear(); // Clear animal display area
        
        ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Arena", "Arena", "Ticketing Office");
        dialog.setHeaderText("Select Building Type");
        Optional<String> type = dialog.showAndWait();
        if (type.isEmpty()) return;

        String color = inputText("Enter color:");
        double length = inputDouble("Enter length:");
        double width = inputDouble("Enter width:");
        String imagePath = inputText("Enter image file path:");

        Building building = switch (type.get()) {
            case "Arena" -> new Arena(color, length, width, imagePath);
            case "Ticketing Office" -> new TicketingOffice(color, length, width, imagePath);
            default -> null;
        };

        if (building != null) circus.addBuilding(building);
    }

 // ===================== DISPLAY ANIMALS WITH IMAGES =====================
    private void displayAnimalsWithImages() {

        
        animalListBox.getChildren().clear(); // Clear previous content

        for (Animal animal : circus.getAnimals()) {

            // ----- IMAGE -----
            ImageView imgView = new ImageView();
            try {
                if (animal.getImagePath() != null && !animal.getImagePath().isEmpty())
                    imgView.setImage(new Image("file:" + animal.getImagePath()));
            } catch (Exception ignored) {}

            imgView.setFitWidth(150); // adjust width
            imgView.setPreserveRatio(true);

            // ----- NAME LABEL -----
            Label nameLabel = new Label(animal.getName());
            nameLabel.setAlignment(Pos.CENTER);

            VBox animalBox = new VBox(10, imgView, nameLabel);
            animalBox.setAlignment(Pos.CENTER);
            animalBox.setPadding(new Insets(10));
            animalBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-radius: 5;");

            animalListBox.getChildren().add(animalBox);
        }

        outputArea.setText("Displaying " + circus.getAnimals().size() + " animals.");
    }

    
    private void displayPersonsWithImages() {
        
        animalListBox.getChildren().clear();   // Clear previous images

        StringBuilder details = new StringBuilder();
        details.append("All Persons:\n");
        details.append("-----------------\n");

        for (Person person : circus.getPersons()) {

            // ----- IMAGE ----- 
            ImageView imgView = new ImageView();
            try {
                if (person.getImagePath() != null && !person.getImagePath().isEmpty())
                    imgView.setImage(new Image("file:" + person.getImagePath()));
            } catch (Exception ignored) {}

            imgView.setFitWidth(150);
            imgView.setPreserveRatio(true);

            // ----- Display name under image -----
            Label nameLabel = new Label(person.getName());
            nameLabel.setAlignment(Pos.CENTER);

            VBox personBox = new VBox(8, imgView, nameLabel);
            personBox.setAlignment(Pos.CENTER);
            personBox.setPadding(new Insets(10));
            personBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-radius: 5;");

            animalListBox.getChildren().add(personBox);

            // ----- TEXT DETAILS FOR OUTPUT AREA -----
            details.append("Type: ").append(person.getClass().getSimpleName()).append("\n");
            details.append("Name: ").append(person.getName()).append("\n");
            details.append("Age: ").append(person.getAge()).append("\n");
            details.append("Job: ").append(person.getJob()).append("\n");
            details.append("Years Worked: ").append(person.getYearsWorked()).append("\n");
            details.append("-----------------\n");
        }

        // Display all details in the output area
        outputArea.setText(details.toString());
    }

    
    

    private void displayBuildingsWithImages() {
        
        animalListBox.getChildren().clear();    // Clear previous images

        StringBuilder details = new StringBuilder();
        details.append("All Buildings:\n");
        details.append("-----------------\n");

        for (Building building : circus.getBuildings()) {

            // ----- IMAGE -----
            ImageView imgView = new ImageView();
            try {
                if (building.getImagePath() != null && !building.getImagePath().isEmpty())
                    imgView.setImage(new Image("file:" + building.getImagePath()));
            } catch (Exception ignored) {}

            imgView.setFitWidth(150);
            imgView.setPreserveRatio(true);

            // ----- Display name under image -----
            Label nameLabel = new Label(building.getClass().getSimpleName());
            nameLabel.setAlignment(Pos.CENTER);

            VBox buildingBox = new VBox(8, imgView, nameLabel);
            buildingBox.setAlignment(Pos.CENTER);
            buildingBox.setPadding(new Insets(10));
            buildingBox.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-background-radius: 5;");

            animalListBox.getChildren().add(buildingBox);

            // ----- TEXT DETAILS FOR OUTPUT AREA -----
            details.append("Type: ").append(building.getClass().getSimpleName()).append("\n");
            details.append("Color: ").append(building.getColor()).append("\n");
            details.append("Length: ").append(building.getLength()).append("\n");
            details.append("Width: ").append(building.getWidth()).append("\n");
            details.append("-----------------\n");
        }

        // Display all details in the output area
        outputArea.setText(details.toString());
    }


    
    private void handleSearchAnimal() {
        String name = inputText("Enter animal name:");
        Animal foundAnimal = null;

        // Search for animal
        for (Animal animal : circus.getAnimals()) {
            if (animal.getName().equalsIgnoreCase(name)) {
                foundAnimal = animal;
                break;
            }
        }

        
        animalListBox.getChildren().clear(); // Clear previous content

        if (foundAnimal != null) {

            // ===== Display name under image =====
            ImageView imgView = new ImageView();
            try {
                if (foundAnimal.getImagePath() != null && !foundAnimal.getImagePath().isEmpty())
                    imgView.setImage(new Image("file:" + foundAnimal.getImagePath()));
            } catch (Exception ignored) {}

            imgView.setFitWidth(200);
            imgView.setPreserveRatio(true);

            Label nameLabel = new Label(foundAnimal.getName());
            nameLabel.setAlignment(Pos.CENTER);

            VBox imageBox = new VBox(10, imgView, nameLabel);
            imageBox.setAlignment(Pos.CENTER);
            imageBox.setPadding(new Insets(20));

            animalListBox.getChildren().add(imageBox);

            // ===== TEXT DETAILS IN OUTPUT AREA =====
            StringBuilder details = new StringBuilder();
            details.append("Animal Details:\n");
            details.append("-----------------\n");
            details.append("Name: ").append(foundAnimal.getName()).append("\n");
            details.append("Age: ").append(foundAnimal.getAge()).append("\n");
            details.append("Species: ").append(foundAnimal.getSpecies()).append("\n");
            details.append("Color: ").append(foundAnimal.getColor()).append("\n");

            // Capture makeSound() and move() output  (OPTIONAL)
            PrintStream originalOut = System.out;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);

            foundAnimal.makeSound();
            foundAnimal.move();

            System.out.flush();
            System.setOut(originalOut);
            String animalActions = baos.toString().trim();

            details.append(animalActions).append("\n");

            outputArea.setText(details.toString());

        } else {
            outputArea.setText("No animal found with the name: " + name);
        }
    }


    private String captureConsole(Runnable task) {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        task.run();
        System.out.flush();
        System.setOut(original);
        return baos.toString();
    }

    private String inputText(String prompt) {
        TextInputDialog d = new TextInputDialog();
        d.setHeaderText(prompt);
        return d.showAndWait().orElse("").trim();
    }

    private int inputInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(inputText(prompt)); }
            catch (NumberFormatException e) {
                new Alert(Alert.AlertType.WARNING, "Enter a valid integer").showAndWait();
            }
        }
    }

    private double inputDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(inputText(prompt)); }
            catch (NumberFormatException e) {
                new Alert(Alert.AlertType.WARNING, "Enter a valid number").showAndWait();
            }
        }
    }
    
    // TICKET CALCULATIONS
    
    private double calculateTicketPrice(String dayOfWeek, double basePrice, String seatLevel, double customerDiscount) {
        double finalPrice = basePrice;

        DayOfWeek dayEnum = DayOfWeek.valueOf(dayOfWeek.toUpperCase());

        if (dayEnum.getDiscount() > 0) {
            finalPrice *= (1 - dayEnum.getDiscount());
        }

        if (customerDiscount > 0) {
            finalPrice *= (1 - customerDiscount);
        }

        switch (seatLevel) {
            case "T-Level" -> finalPrice *= 2;
            case "Upper Level" -> finalPrice *= 1.5;
            default -> {
                // Lower Level = no change
            }
        }

        return finalPrice;
    }

    private String getSingleTicketDetails(String dayOfWeek, double basePrice, String seatLevel, double customerDiscount) {
        double price = basePrice;
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Regular ticket price: $%.2f%n", basePrice));

        DayOfWeek dayEnum = DayOfWeek.valueOf(dayOfWeek.toUpperCase());

        if (dayEnum.getDiscount() > 0) {
            double oldPrice = price;
            price *= (1 - dayEnum.getDiscount());

            sb.append(String.format(
                    "Day: %s, Day Discount: %.0f%% off: $%.2f -> $%.2f%n",
                    dayOfWeek.toUpperCase(),
                    dayEnum.getDiscount() * 100,
                    oldPrice,
                    price
            ));
        } else {
            sb.append("Day: " + dayOfWeek.toUpperCase() + " (No discount)\n");
        }

        if (customerDiscount > 0) {
            double oldPrice = price;
            price *= (1 - customerDiscount);

            sb.append(String.format(
                    "Customer Type Discount: %.0f%% off: $%.2f -> $%.2f%n",
                    customerDiscount * 100,
                    oldPrice,
                    price
            ));
        } else {
            sb.append("Customer Type Discount: None\n");
        }

        switch (seatLevel) {
            case "T-Level" -> {
                double oldPrice = price;
                price *= 2;
                sb.append(String.format("T-Level Seat double price: $%.2f -> $%.2f%n", oldPrice, price));
            }
            case "Upper Level" -> {
                double oldPrice = price;
                price *= 1.5;
                sb.append(String.format("Upper Level Seat 50%% extra: $%.2f -> $%.2f%n", oldPrice, price));
            }
            default -> sb.append("Lower Level Seat: No extra charge\n");
        }

        sb.append(String.format("Final Single Ticket Price: $%.2f%n", price));

        return sb.toString();
    }
    
}
