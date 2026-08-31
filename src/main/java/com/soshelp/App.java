package com.soshelp;

import com.soshelp.model.HelpType;
import com.soshelp.model.SOSRequest;
import com.soshelp.model.User;
import com.soshelp.service.SOSHelpService;

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

import java.util.Set;

public class App extends Application {

    private final SOSHelpService service = new SOSHelpService();

    @Override
    public void start(Stage stage) {

        seedUsers();

        // ---------- TITLE ----------
        Label title = new Label("SOS HELP");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setTextFill(Color.web("#172033"));

        Label status = new Label("You're currently safe");
        status.setFont(Font.font("Arial", 15));
        status.setTextFill(Color.web("#555555"));

        Label location = new Label("📍 Current Location");
        location.setFont(Font.font("Arial", 14));

        // ---------- SOS BUTTON ----------
        Button sosButton = new Button("🆘\nSEND SOS");

        sosButton.setPrefSize(220, 160);
        sosButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        sosButton.setTextFill(Color.WHITE);

        sosButton.setStyle(
                "-fx-background-color: #D62828;" +
                "-fx-background-radius: 110;" +
                "-fx-cursor: hand;"
        );

        // ---------- HELP OPTIONS ----------
        Label question = new Label("What do you need help with?");
        question.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        question.setTextFill(Color.web("#172033"));

        Button carButton = new Button("🚗  Car Trouble");
        Button jumpButton = new Button("🪫  Jump Start");
        Button repairButton = new Button("🔧  Repair");
        Button homeButton = new Button("🏠  Home Help");
        Button otherButton = new Button("📦  Other");

        Button[] helpButtons = {
                carButton,
                jumpButton,
                repairButton,
                homeButton,
                otherButton
        };

        for (Button button : helpButtons) {
            button.setPrefWidth(150);
            button.setPrefHeight(45);
            button.setFont(Font.font("Arial", 14));
            button.setStyle(
                    "-fx-background-color: #F1F3F5;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-radius: 10;" +
                    "-fx-cursor: hand;"
            );
        }

        // ---------- STATUS MESSAGE ----------
        Label message = new Label("Select what you need help with.");
        message.setFont(Font.font("Arial", 14));
        message.setTextFill(Color.web("#555555"));

        // ---------- BUTTON ACTIONS ----------
        carButton.setOnAction(event ->
                message.setText("🚗 Car trouble selected"));

        jumpButton.setOnAction(event ->
                message.setText("🪫 Jump start selected"));

        repairButton.setOnAction(event ->
                message.setText("🔧 Repair selected"));

        homeButton.setOnAction(event ->
                message.setText("🏠 Home help selected"));

        otherButton.setOnAction(event ->
                message.setText("📦 Other help selected"));

        // ---------- SOS ACTION ----------
        sosButton.setOnAction(event -> {

            message.setText("🔴 SOS sent! Searching for nearby helpers...");

            try {

                SOSRequest request = new SOSRequest(
                        "Terrik",
                        HelpType.OTHER,
                        40.7128,
                        -74.0060,
                        10
                );

                var matches = service.findNearbyHelpers(request);

                if (matches.isEmpty()) {
                    message.setText(
                            "🔴 SOS sent — no nearby helpers found."
                    );
                } else {
                    message.setText(
                            "🟢 SOS sent — " + matches.size()
                                    + " nearby helper(s) found!"
                    );
                }

            } catch (Exception ex) {

                message.setText(
                        "SOS sent. Searching for nearby help..."
                );
            }
        });

        // ---------- HELP BUTTON GRID ----------
        GridPane helpGrid = new GridPane();
        helpGrid.setHgap(10);
        helpGrid.setVgap(10);
        helpGrid.setAlignment(Pos.CENTER);

        helpGrid.add(carButton, 0, 0);
        helpGrid.add(jumpButton, 1, 0);
        helpGrid.add(repairButton, 0, 1);
        helpGrid.add(homeButton, 1, 1);
        helpGrid.add(otherButton, 0, 2);

        // ---------- BOTTOM NAVIGATION ----------
        Button homeNav = new Button("🏠 Home");
        Button requestsNav = new Button("📋 Requests");
        Button profileNav = new Button("👤 Profile");

        Button[] navButtons = {
                homeNav,
                requestsNav,
                profileNav
        };

        for (Button button : navButtons) {
            button.setPrefWidth(120);
            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-font-size: 13px;" +
                    "-fx-cursor: hand;"
            );
        }

        HBox navigation = new HBox(20,
                homeNav,
                requestsNav,
                profileNav
        );

        navigation.setAlignment(Pos.CENTER);

        // ---------- MAIN LAYOUT ----------
        VBox root = new VBox(18);

        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: white;");

        root.getChildren().addAll(
                title,
                status,
                location,
                sosButton,
                question,
                helpGrid,
                message,
                new Separator(),
                navigation
        );

        Scene scene = new Scene(root, 450, 700);

        stage.setTitle("SOS Help");
        stage.setScene(scene);
        stage.show();
    }

    private void seedUsers() {

        service.registerUser(
                new User(
                        "Ava",
                        40.7131,
                        -74.0055,
                        Set.of(HelpType.MEDICAL, HelpType.SAFETY)
                )
        );

        service.registerUser(
                new User(
                        "Noah",
                        40.7306,
                        -73.9352,
                        Set.of(HelpType.TRANSPORT, HelpType.FOOD)
                )
        );

        service.registerUser(
                new User(
                        "Mia",
                        40.7060,
                        -74.0086,
                        Set.of(HelpType.MEDICAL, HelpType.FOOD)
                )
        );

        service.registerUser(
                new User(
                        "Liam",
                        40.7580,
                        -73.9855,
                        Set.of(HelpType.SAFETY, HelpType.OTHER)
                )
        );
    }

    public static void main(String[] args) {
        launch();
    }
}