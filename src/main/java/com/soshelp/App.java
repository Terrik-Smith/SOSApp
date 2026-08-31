package com.soshelp;

import com.soshelp.model.HelpType;
import com.soshelp.model.SOSRequest;
import com.soshelp.model.User;
import com.soshelp.service.SOSHelpService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Set;

public class App extends Application {
    private final SOSHelpService service = new SOSHelpService();

    @Override
    public void start(Stage stage) {
        seedUsers();

        Label title = new Label("SOS Help");
        TextField requesterField = new TextField();
        requesterField.setPromptText("Your name");

        ComboBox<HelpType> helpTypeBox = new ComboBox<>();
        helpTypeBox.getItems().setAll(HelpType.values());
        helpTypeBox.getSelectionModel().select(HelpType.MEDICAL);

        TextField latitudeField = new TextField("40.7128");
        TextField longitudeField = new TextField("-74.0060");
        TextField radiusField = new TextField("10");

        Button findButton = new Button("Find Nearby Helpers");
        ListView<String> resultList = new ListView<>();

        findButton.setOnAction(event -> {
            resultList.getItems().clear();
            try {
                SOSRequest request = new SOSRequest(
                        requesterField.getText().isBlank() ? "Anonymous" : requesterField.getText().trim(),
                        helpTypeBox.getValue(),
                        Double.parseDouble(latitudeField.getText().trim()),
                        Double.parseDouble(longitudeField.getText().trim()),
                        Double.parseDouble(radiusField.getText().trim())
                );

                var matches = service.findNearbyHelpers(request);
                if (matches.isEmpty()) {
                    resultList.getItems().add("No nearby users available for this request type.");
                } else {
                    for (var match : matches) {
                        resultList.getItems().add(match.user().getName() + " - " + String.format("%.2f km", match.distanceKm()));
                    }
                }
            } catch (NumberFormatException ex) {
                resultList.getItems().add("Please enter valid numbers for latitude, longitude, and radius.");
            }
        });

        VBox root = new VBox(10,
                title,
                new Label("Requester"), requesterField,
                new Label("Help Type"), helpTypeBox,
                new Label("Latitude"), latitudeField,
                new Label("Longitude"), longitudeField,
                new Label("Search Radius (km)"), radiusField,
                findButton,
                resultList
        );

        root.setPadding(new Insets(16));
        stage.setTitle("SOS Help");
        stage.setScene(new Scene(root, 420, 560));
        stage.show();
    }

    private void seedUsers() {
        service.registerUser(new User("Ava", 40.7131, -74.0055, Set.of(HelpType.MEDICAL, HelpType.SAFETY)));
        service.registerUser(new User("Noah", 40.7306, -73.9352, Set.of(HelpType.TRANSPORT, HelpType.FOOD)));
        service.registerUser(new User("Mia", 40.7060, -74.0086, Set.of(HelpType.MEDICAL, HelpType.FOOD)));
        service.registerUser(new User("Liam", 40.7580, -73.9855, Set.of(HelpType.SAFETY, HelpType.OTHER)));
    }

    public static void main(String[] args) {
        launch();
    }
}
