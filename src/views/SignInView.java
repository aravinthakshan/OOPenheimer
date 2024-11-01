package views;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import controllers.SignInController;

public class SignInView {
    private VBox view;
    private TextField nameField;
    private TextField emailField;
    private PasswordField passwordField;
    private ChoiceBox<String> userTypeBox;
    private Button signInButton;
    private Label messageLabel;

    public SignInView() {
        nameField = new TextField();
        nameField.setPromptText("Name");

        emailField = new TextField();
        emailField.setPromptText("Email");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        userTypeBox = new ChoiceBox<>();
        userTypeBox.getItems().addAll("Patient", "Doctor");

        signInButton = new Button("Sign In");
        messageLabel = new Label();

        view = new VBox(10, nameField, emailField, passwordField, userTypeBox, signInButton, messageLabel);
        signInButton.setOnAction(event -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String userType = userTypeBox.getValue();
            new SignInController().handleSignIn(name, email, password, userType, messageLabel);
        });
    }

    public VBox getView() {
        return view;
    }
}
