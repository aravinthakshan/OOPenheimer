package controllers;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import utils.Authentication;
import views.PatientDashboardView;
import views.DoctorDashboardView;

public class LoginController {
    public void handleLogin(String email, String password, Label messageLabel, String userType) {
        if (userType.equals("Patient") && Authentication.authenticatePatient(email, password)) {
            Stage patientStage = new Stage();
            patientStage.setScene(new Scene(new PatientDashboardView().getView(), 800, 600));
            patientStage.setTitle("Patient Dashboard");
            patientStage.show();
        } else if (userType.equals("Doctor") && Authentication.authenticateDoctor(email, password)) {
            Stage doctorStage = new Stage();
            doctorStage.setScene(new Scene(new DoctorDashboardView().getView(), 800, 600));
            doctorStage.setTitle("Doctor Dashboard");
            doctorStage.show();
        } else {
            messageLabel.setText("Invalid email or password.");
        }
    }
}
