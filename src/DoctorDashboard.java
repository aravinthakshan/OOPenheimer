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
import java.io.File;

public class DoctorDashboard {
    private Stage stage;
    private String username;
    private ListView<String> appointmentListView; // Declare as a class-level variable

    public DoctorDashboard(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        showDashboard();
    }

    private void showDashboard() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Load Doctor Details
        String specialization = "Unknown";
        String contact = "Unknown";

        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("credentials.json");
            JSONObject credentials = (JSONObject) parser.parse(reader);
            JSONObject doctorDetails = (JSONObject) ((JSONObject) credentials.get("doctor")).get(username);

            if (doctorDetails != null) {
                specialization = (String) doctorDetails.get("specialization");
                contact = (String) doctorDetails.get("contact");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Welcome Message
        Label welcomeLabel = new Label("Welcome, " + username);
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        // Doctor Details
        Label specializationLabel = new Label("Specialization: " + specialization);
        Label contactLabel = new Label("Contact: " + contact);
        specializationLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");
        contactLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");

        // Appointment List Label
        Label appointmentLabel = new Label("Your Appointments:");
        appointmentLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        // Appointment List View
        appointmentListView = new ListView<>(); // Initialize the ListView
        ObservableList<String> appointments = FXCollections.observableArrayList();
        loadAppointments(appointments);
        appointmentListView.setItems(appointments);
        appointmentListView.setStyle("-fx-border-radius: 5px; -fx-background-color: #f9f9f9;");

        // Buttons
        Button viewDetailsButton = new Button("View Patient Details");
        viewDetailsButton.setStyle(
                "-fx-background-color: #0078d4; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        viewDetailsButton.setOnAction(e -> showPatientDetails());

        Button rescheduleButton = new Button("Reschedule Appointment");
        rescheduleButton.setStyle(
                "-fx-background-color: #ffc107; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        rescheduleButton.setOnAction(e -> rescheduleAppointment());

        Button backButton = new Button("Back to Main Menu");
        backButton.setStyle(
                "-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        backButton.setOnAction(e -> new MainApp().start(stage));

        layout.getChildren().addAll(welcomeLabel, specializationLabel, contactLabel, appointmentLabel,
                appointmentListView, viewDetailsButton, rescheduleButton, backButton);

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
    }

    private void loadAppointments(ObservableList<String> appointments) {
        try {
            File file = new File("appointments.json");
            if (!file.exists()) {
                System.out.println("appointments.json not found!");
                return;
            }

            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(file);
            JSONArray appointmentsArray = (JSONArray) parser.parse(reader);

            for (Object obj : appointmentsArray) {
                JSONObject appointment = (JSONObject) obj;
                String doctorName = appointment.get("doctor").toString();

                if (doctorName.equals(username)) {
                    String patient = appointment.get("patient").toString();
                    String date = appointment.get("date").toString();
                    String time = appointment.get("time").toString();
                    appointments.add("Patient: " + patient + ", Date: " + date + ", Time: " + time);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPatientDetails() {
        String selectedAppointment = appointmentListView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            showAlert("Please select an appointment to view details.");
            return;
        }

        String patientName = selectedAppointment.split(",")[0].split(": ")[1];
        Alert detailsAlert = new Alert(Alert.AlertType.INFORMATION);
        detailsAlert.setTitle("Patient Details");
        detailsAlert.setHeaderText("Details for Patient: " + patientName);
        detailsAlert.setContentText("Name: " + patientName
                + "\nAge: 29\nSex: Male\nBlood Group: B+\nMedical History: No known allergies. Previous surgeries: None.");
        detailsAlert.show();
    }

    private void rescheduleAppointment() {
        String selectedAppointment = appointmentListView.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            showAlert("Please select an appointment to reschedule.");
            return;
        }

        String patientName = selectedAppointment.split(",")[0].split(": ")[1];
        new AppointmentBookingPage(stage, patientName);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
