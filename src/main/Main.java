package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.LoginView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getView(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hospital Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
