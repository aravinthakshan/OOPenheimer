package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utils.JSONUtils;
import org.json.JSONObject;

public class PatientDashboardController {
    private Label patientNameLabel;
    private ListView<String> appointmentListView;
    private TextField emergencyContactField;

    public PatientDashboardController(Label patientNameLabel, ListView<String> appointmentListView,
            TextField emergencyContactField) {
        this.patientNameLabel = patientNameLabel;
        this.appointmentListView = appointmentListView;
        this.emergencyContactField = emergencyContactField;
    }

    // Load patient details from JSON file
    public void loadPatientDetails(String email) {
        JSONObject patients = JSONUtils.readJSONFile("data/patients.json");
        for (String key : patients.keySet()) {
            JSONObject patient = patients.getJSONObject(key);
            if (patient.getString("email").equals(email)) {
                patientNameLabel.setText(patient.getString("name"));
                emergencyContactField.setText(patient.getString("emergencyContact"));
                // Load additional details as needed
                break;
            }
        }
    }

    // Handle appointment scheduling or other actions
    public void handleAppointmentAction() {
        // Implement appointment scheduling logic
    }
}
