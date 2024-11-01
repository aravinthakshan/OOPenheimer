package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utils.JSONUtils;
import org.json.JSONObject;

public class DoctorDashboardController {
    private Label doctorNameLabel;
    private ListView<String> scheduleListView;

    public DoctorDashboardController(Label doctorNameLabel, ListView<String> scheduleListView) {
        this.doctorNameLabel = doctorNameLabel;
        this.scheduleListView = scheduleListView;
    }

    // Load doctor details from JSON file
    public void loadDoctorDetails(String email) {
        JSONObject doctors = JSONUtils.readJSONFile("data/doctors.json");
        for (String key : doctors.keySet()) {
            JSONObject doctor = doctors.getJSONObject(key);
            if (doctor.getString("email").equals(email)) {
                doctorNameLabel.setText(doctor.getString("name"));
                // Load the doctor's schedule or other details
                break;
            }
        }
    }

    // Handle doctor-specific actions
    public void handleScheduleManagement() {
        // Implement logic to manage the doctor's schedule
    }
}
