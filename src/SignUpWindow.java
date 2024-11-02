import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpWindow {
    private Stage stage;
    private String userType;

    public SignUpWindow(Stage stage, String userType) {
        this.stage = stage;
        this.userType = userType;
        showSignUp();
    }

    private void showSignUp() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Title
        Label titleLabel = new Label(userType.equals("doctor") ? "Doctor Sign Up" : "Patient Sign Up");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        // Input Fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Additional Fields for Doctor
        TextField specializationField = new TextField();
        specializationField.setPromptText("Specialization (e.g., Cardiologist)");

        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");

        // Patient-specific fields
        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        ComboBox<String> sexComboBox = new ComboBox<>();
        sexComboBox.setPromptText("Select Sex");
        sexComboBox.getItems().addAll("Male", "Female", "Other");

        TextField bloodGroupField = new TextField();
        bloodGroupField.setPromptText("Blood Group (e.g., A+, O-)");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle(
                "-fx-background-color: #0078d4; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        signUpButton.setOnAction(e -> handleSignUp(usernameField.getText(), passwordField.getText(),
                specializationField.getText(), contactField.getText(), ageField.getText(),
                sexComboBox.getValue(), bloodGroupField.getText()));

        // Add elements to layout
        layout.getChildren().addAll(titleLabel, usernameField, passwordField);

        // Add doctor-specific fields only if user is a doctor
        if (userType.equals("doctor")) {
            layout.getChildren().addAll(specializationField, contactField);
        } else {
            // Add patient-specific fields
            layout.getChildren().addAll(ageField, sexComboBox, bloodGroupField);
        }

        layout.getChildren().add(signUpButton);

        Scene scene = new Scene(layout, 350, 450);
        stage.setScene(scene);
    }

    private void handleSignUp(String username, String password, String specialization, String contact,
            String age, String sex, String bloodGroup) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject credentials;

            // Read existing data
            try (FileReader reader = new FileReader("credentials.json")) {
                credentials = (JSONObject) parser.parse(reader);
            } catch (IOException | ParseException e) {
                credentials = new JSONObject();
            }

            if (!credentials.containsKey(userType)) {
                credentials.put(userType, new JSONObject());
            }

            JSONObject userCredentials = (JSONObject) credentials.get(userType);
            if (userCredentials.containsKey(username)) {
                showAlert("Username already exists.");
                return;
            }

            // Create a user object with the necessary details
            JSONObject userDetails = new JSONObject();
            userDetails.put("password", password);

            if (userType.equals("doctor")) {
                userDetails.put("specialization", specialization);
                userDetails.put("contact", contact);
            } else if (userType.equals("patient")) {
                userDetails.put("age", age);
                userDetails.put("sex", sex);
                userDetails.put("bloodGroup", bloodGroup);
            }

            userCredentials.put(username, userDetails); // Store the details

            // Save updated data back to the file
            try (FileWriter writer = new FileWriter("credentials.json")) {
                writer.write(credentials.toJSONString());
            }

            showAlert("Sign-up successful. Please login.");
            new LoginWindow(stage, userType);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error saving credentials.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
