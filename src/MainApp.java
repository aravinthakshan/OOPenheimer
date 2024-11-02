import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox mainMenu = new VBox(30);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setStyle("-fx-background-color: #f8f9fa; -fx-padding: 40px;");

        Label titleLabel = new Label("Healthcare Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Button doctorLogin = new Button("Doctor Login");
        Button patientLogin = new Button("Patient Login");

        String buttonStyle = "-fx-background-color: #0078d4; -fx-text-fill: white; -fx-font-size: 16px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;";
        doctorLogin.setStyle(buttonStyle);
        patientLogin.setStyle(buttonStyle);

        doctorLogin.setOnMouseEntered(e -> doctorLogin.setStyle("-fx-background-color: #005a9e; -fx-text-fill: white; "
                +
                "-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;"));
        doctorLogin.setOnMouseExited(e -> doctorLogin.setStyle(buttonStyle));

        patientLogin
                .setOnMouseEntered(e -> patientLogin.setStyle("-fx-background-color: #005a9e; -fx-text-fill: white; " +
                        "-fx-font-size: 16px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;"));
        patientLogin.setOnMouseExited(e -> patientLogin.setStyle(buttonStyle));

        doctorLogin.setOnAction(e -> new LoginWindow(primaryStage, "doctor"));
        patientLogin.setOnAction(e -> new LoginWindow(primaryStage, "patient"));

        mainMenu.getChildren().addAll(titleLabel, doctorLogin, patientLogin);

        Scene scene = new Scene(mainMenu, 600, 300);
        primaryStage.setTitle("Healthcare Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
