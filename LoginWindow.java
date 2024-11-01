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
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");

        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText()));
        signUpButton.setOnAction(e -> new SignUpWindow(stage, userType));

        layout.getChildren().addAll(usernameField, passwordField, loginButton, signUpButton);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
    }

    private void handleLogin(String username, String password) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("credentials.json");
            JSONObject credentials = (JSONObject) parser.parse(reader);
            JSONObject userCredentials = (JSONObject) credentials.get(userType);

            if (userCredentials != null && userCredentials.containsKey(username) &&
                    userCredentials.get(username).equals(password)) {
                if (userType.equals("doctor")) {
                    new DoctorDashboard(stage, username);
                } else {
                    new PatientDashboard(stage, username);
                }
            } else {
                showAlert("Invalid username or password.");
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
