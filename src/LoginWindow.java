import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class LoginWindow {
    private Stage stage;
    private String userType;

    public LoginWindow(Stage stage, String userType) {
        this.stage = stage;
        this.userType = userType;
        showLogin();
    }

    private void showLogin() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));
        layout.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        Label titleLabel = new Label(userType.equals("doctor") ? "Doctor Login" : "Patient Login");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle(
                "-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-radius: 5px; -fx-border-color: #cccccc;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle(
                "-fx-padding: 10px; -fx-background-color: #f5f5f5; -fx-border-radius: 5px; -fx-border-color: #cccccc;");

        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: #0078d4; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle(
                "-fx-background-color: #0078d4; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText()));
        signUpButton.setOnAction(e -> new SignUpWindow(stage, userType));

        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, signUpButton);

        Scene scene = new Scene(layout, 350, 300);
        stage.setScene(scene);
    }

    private void handleLogin(String username, String password) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("credentials.json");
            JSONObject credentials = (JSONObject) parser.parse(reader);
            JSONObject userCredentials = (JSONObject) credentials.get(userType);

            if (userCredentials != null && userCredentials.containsKey(username)) {
                JSONObject userDetails = (JSONObject) userCredentials.get(username);
                String storedPassword = (String) userDetails.get("password");
                if (storedPassword.equals(password)) {
                    if (userType.equals("doctor")) {
                        new DoctorDashboard(stage, username);
                    } else {
                        new PatientDashboard(stage, username);
                    }
                } else {
                    showAlert("Invalid password.");
                }
            } else {
                showAlert("Invalid username.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error reading credentials file.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
