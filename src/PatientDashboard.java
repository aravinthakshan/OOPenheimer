import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;

public class PatientDashboard {
    private Stage stage;
    private String username;
    private ComboBox<String> doctorComboBox;
    private DatePicker datePicker;
    private ComboBox<String> timeSlotComboBox;

    public PatientDashboard(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        showDashboard();
    }

    private void showDashboard() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("vbox");
        layout.getChildren().add(new Label("Welcome, " + username));

        // Book Appointment Section
        doctorComboBox = new ComboBox<>();
        doctorComboBox.getStyleClass().add("combo-box");
        datePicker = new DatePicker();
        datePicker.getStyleClass().add("date-picker");
        timeSlotComboBox = new ComboBox<>();
        timeSlotComboBox.getStyleClass().add("combo-box");
        Button bookAppointmentButton = new Button("Book Appointment");
        Button backButton = new Button("Back to Main Menu");

        loadDoctors();
        loadTimeSlots();

        bookAppointmentButton.setOnAction(e -> bookAppointment());
        backButton.setOnAction(e -> new MainApp().start(stage)); // Navigate back to main menu

        layout.getChildren().addAll(
                new Label("Book an Appointment:"),
                new Label("Select Doctor:"), doctorComboBox,
                new Label("Select Date:"), datePicker,
                new Label("Select Time Slot:"), timeSlotComboBox,
                bookAppointmentButton,
                backButton);

        Scene scene = new Scene(layout, 400, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());
        stage.setScene(scene);
    }

    private void loadDoctors() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("credentials.json");
            JSONObject credentials = (JSONObject) parser.parse(reader);
            JSONObject doctorCredentials = (JSONObject) credentials.get("doctor");

            if (doctorCredentials != null) {
                doctorComboBox.setItems(FXCollections.observableArrayList(doctorCredentials.keySet()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTimeSlots() {
        ObservableList<String> timeSlots = FXCollections.observableArrayList(
                "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM");
        timeSlotComboBox.setItems(timeSlots);
    }

    private void bookAppointment() {
        String doctor = doctorComboBox.getValue();
        String date = datePicker.getValue() != null ? datePicker.getValue().toString() : null;
        String time = timeSlotComboBox.getValue();

        if (doctor == null || date == null || time == null) {
            showAlert("Please fill all the fields.");
            return;
        }

        try {
            JSONParser parser = new JSONParser();
            JSONArray appointments;

            try (FileReader reader = new FileReader("appointments.json")) {
                appointments = (JSONArray) parser.parse(reader);
            } catch (Exception e) {
                appointments = new JSONArray();
            }

            JSONObject newAppointment = new JSONObject();
            newAppointment.put("doctor", doctor);
            newAppointment.put("patient", username);
            newAppointment.put("date", date);
            newAppointment.put("time", time);

            appointments.add(newAppointment);

            try (FileWriter writer = new FileWriter("appointments.json")) {
                writer.write(appointments.toJSONString());
            }

            showAlert("Appointment booked successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error booking appointment.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
