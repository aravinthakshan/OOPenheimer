package controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import utils.JSONUtils;

public class AppointmentController {
    public void scheduleAppointment(String patientName, String doctorName, String date, Label messageLabel) {
        JSONObject appointment = new JSONObject();
        appointment.put("patientName", patientName);
        appointment.put("doctorName", doctorName);
        appointment.put("date", date);

        JSONUtils.writeToJSONFile("data/appointments.json", appointment);
        messageLabel.setText("Appointment scheduled successfully!");
    }

    public void loadAppointments(ListView<String> appointmentListView) {
        JSONObject appointments = JSONUtils.readJSONFile("data/appointments.json");
        appointments.keys().forEachRemaining(key -> {
            JSONObject appointment = appointments.getJSONObject(key);
            String appointmentDetails = "Patient: " + appointment.getString("patientName") +
                    ", Doctor: " + appointment.getString("doctorName") +
                    ", Date: " + appointment.getString("date");
            appointmentListView.getItems().add(appointmentDetails);
        });
    }
}
