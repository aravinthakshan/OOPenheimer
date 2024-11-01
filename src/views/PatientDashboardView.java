package views;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import controllers.PatientDashboardController;

public class PatientDashboardView {
    private VBox view;
    private Label welcomeLabel;
    private ListView<String> appointmentListView;
    private TextField emergencyContactField; // Declare the TextField

    public PatientDashboardView() {
        welcomeLabel = new Label("Welcome, Patient!");
        appointmentListView = new ListView<>();
        emergencyContactField = new TextField(); // Initialize the TextField

        view = new VBox(10, welcomeLabel, appointmentListView, emergencyContactField);

        // Pass the required arguments to the PatientDashboardController constructor
        PatientDashboardController controller = new PatientDashboardController(
                welcomeLabel, appointmentListView, emergencyContactField);
        controller.loadPatientDetails("patient@example.com"); // Example email for loading details
    }

    public VBox getView() {
        return view;
    }
}
