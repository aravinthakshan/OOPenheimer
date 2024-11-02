import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
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
import org.json.simple.parser.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Visit {
    private final StringProperty doctor;
    private final StringProperty date;
    private final StringProperty summary;

    public Visit(String doctor, String date, String summary) {
        this.doctor = new SimpleStringProperty(doctor);
        this.date = new SimpleStringProperty(date);
        this.summary = new SimpleStringProperty(summary);
    }

    public String getDoctor() {
        return doctor.get();
    }

    public StringProperty doctorProperty() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor.set(doctor);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getSummary() {
        return summary.get();
    }

    public StringProperty summaryProperty() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary.set(summary);
    }
}

public class PatientDashboard {
    private Stage stage;
    private String username;

    public PatientDashboard(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        showDashboard();
    }

    private void showDashboard() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        Label welcomeLabel = new Label("Welcome, " + username);
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label patientDetailsLabel = new Label("Patient Details:");
        patientDetailsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        Label bloodGroupLabel = new Label("Blood Group: B+");
        Label ageLabel = new Label("Age: 29");
        Label sexLabel = new Label("Sex: Male");

        Button bookAppointmentButton = new Button("Book Appointment");
        bookAppointmentButton.setStyle("-fx-background-color: #0078d4; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        bookAppointmentButton.setOnAction(e -> new AppointmentBookingPage(stage, username)); // Navigate to booking page

        Label previousVisitsLabel = new Label("Previous Visits:");
        previousVisitsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        TableView<Visit> previousVisitsTable = createPreviousVisitsTable();

        layout.getChildren().addAll(
                welcomeLabel,
                patientDetailsLabel, bloodGroupLabel, ageLabel, sexLabel,
                bookAppointmentButton,
                previousVisitsLabel, previousVisitsTable);

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
    }

    private TableView<Visit> createPreviousVisitsTable() {
        TableView<Visit> table = new TableView<>();
        TableColumn<Visit, String> doctorColumn = new TableColumn<>("Doctor");
        doctorColumn.setCellValueFactory(data -> data.getValue().doctorProperty());
        TableColumn<Visit, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> data.getValue().dateProperty());
        TableColumn<Visit, String> summaryColumn = new TableColumn<>("Summary");
        summaryColumn.setCellValueFactory(data -> data.getValue().summaryProperty());
        table.getColumns().addAll(doctorColumn, dateColumn, summaryColumn);

        // Dummy data
        table.setItems(FXCollections.observableArrayList(
                new Visit("Dr. Smith", "2024-09-15", "Routine Checkup"),
                new Visit("Dr. Jones", "2024-08-20", "Follow-up")));

        return table;
    }
}

class AppointmentBookingPage {
    private Stage stage;
    private String username;
    private ComboBox<String> doctorComboBox;
    private DatePicker datePicker;
    private ComboBox<String> timeSlotComboBox;
    private ObservableList<String> allTimeSlots;

    public AppointmentBookingPage(Stage stage, String username) {
        this.stage = stage;
        this.username = username;
        showBookingPage();
    }

    private void showBookingPage() {
        Label titleLabel = new Label("Book or Reschedule Appointment");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        doctorComboBox = new ComboBox<>();
        doctorComboBox.setPromptText("Select Doctor");
        doctorComboBox.getItems().addAll("Dr. Smith", "Dr. Jones");

        datePicker = new DatePicker();
        datePicker.setPromptText("Select Date");

        timeSlotComboBox = new ComboBox<>();
        timeSlotComboBox.setPromptText("Select Time Slot");
        allTimeSlots = FXCollections.observableArrayList(
                "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM");
        timeSlotComboBox.setItems(FXCollections.observableArrayList(allTimeSlots));

        Button checkAvailabilityButton = new Button("Check Availability");
        checkAvailabilityButton.setOnAction(e -> checkAvailability());

        Button confirmButton = new Button("Confirm Appointment");
        confirmButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        confirmButton.setOnAction(e -> bookAppointment());

        Button backButton = new Button("Back to Dashboard");
        backButton.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        backButton.setOnAction(e -> new PatientDashboard(stage, username));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Select Doctor:"), 0, 0);
        gridPane.add(doctorComboBox, 1, 0);
        gridPane.add(new Label("Select Date:"), 0, 1);
        gridPane.add(datePicker, 1, 1);
        gridPane.add(new Label("Select Time Slot:"), 0, 2);
        gridPane.add(timeSlotComboBox, 1, 2);
        gridPane.add(checkAvailabilityButton, 1, 3);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(confirmButton, backButton);

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.getChildren().addAll(titleLabel, gridPane, buttonBox);

        Scene scene = new Scene(mainLayout, 500, 450);
        stage.setScene(scene);
    }

    private void checkAvailability() {
        String doctor = doctorComboBox.getValue();
        String date = datePicker.getValue() != null ? datePicker.getValue().toString() : null;

        if (doctor == null || date == null) {
            showAlert("Please select both doctor and date.");
            return;
        }

        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("appointments.json");
            JSONArray appointmentsArray = (JSONArray) parser.parse(reader);

            timeSlotComboBox.setItems(FXCollections.observableArrayList(allTimeSlots));

            ObservableList<String> unavailableSlots = FXCollections.observableArrayList();
            for (Object obj : appointmentsArray) {
                JSONObject appointment = (JSONObject) obj;
                if (appointment.get("doctor").equals(doctor) && appointment.get("date").equals(date)) {
                    unavailableSlots.add((String) appointment.get("time"));
                }
            }

            timeSlotComboBox.getItems().removeAll(unavailableSlots);

            showAlert("Available slots updated. Unavailable slots have been removed.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            showAlert("Error loading appointments data.");
        }
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

            appointments.removeIf(obj -> ((JSONObject) obj).get("patient").equals(username));

            appointments.add(newAppointment);

            try (FileWriter writer = new FileWriter("appointments.json")) {
                writer.write(appointments.toJSONString());
            }

            showAlert("Appointment booked or rescheduled successfully!");
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
