package views;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import controllers.LoginController;

public class LoginView {
    private VBox view;
    private TextField emailField;
    private PasswordField passwordField;
    private ChoiceBox<String> userTypeBox;
    private Button loginButton;
    private Label messageLabel;

    public LoginView() {
        emailField = new TextField();
        emailField.setPromptText("Email");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        userTypeBox = new ChoiceBox<>();
        userTypeBox.getItems().addAll("Patient", "Doctor");

        loginButton = new Button("Login");
        messageLabel = new Label();

        view = new VBox(10, emailField, passwordField, userTypeBox, loginButton, messageLabel);
        loginButton.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String userType = userTypeBox.getValue();
            new LoginController().handleLogin(email, password, messageLabel, userType);
        });
    }

    public VBox getView() {
        return view;
    }
}
