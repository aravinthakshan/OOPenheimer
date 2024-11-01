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
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button signUpButton = new Button("Sign Up");

        signUpButton.setOnAction(e -> handleSignUp(usernameField.getText(), passwordField.getText()));

        layout.getChildren().addAll(usernameField, passwordField, signUpButton);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
    }

    private void handleSignUp(String username, String password) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject credentials;

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

            userCredentials.put(username, password);

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
