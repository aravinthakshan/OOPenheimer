import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class DoctorDashboard {
    private Stage stage;
    private String username;
    private ListView<String> appointmentListView;

    public DoctorDashboard(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        showDashboard();
    }

    private void showDashboard() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("vbox");
        layout.getChildren().add(new Label("Welcome, Dr. " + username));

        // Appointment List
        appointmentListView = new ListView<>();
        appointmentListView.getStyleClass().add("list-view");
        loadAppointments();

        // Back to Main Menu Button
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(e -> new MainApp().start(stage)); // Navigate back to main menu

        layout.getChildren().addAll(new Label("Your Appointments:"), appointmentListView, backButton);

        Scene scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        stage.setScene(scene);
    }

    private void loadAppointments() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("appointments.json");
            JSONArray appointmentsArray = (JSONArray) parser.parse(reader);

            for (Object obj : appointmentsArray) {
                JSONObject appointment = (JSONObject) obj;
                if (appointment.get("doctor").equals(username)) {
                    String patient = (String) appointment.get("patient");
                    String date = (String) appointment.get("date");
                    String time = (String) appointment.get("time");
                    appointmentListView.getItems().add("Patient: " + patient + ", Date: " + date + ", Time: " + time);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
