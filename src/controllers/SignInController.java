package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.JSONUtils;
import org.json.JSONObject;

public class SignInController {
    public void handleSignIn(String name, String email, String password, String userType, Label messageLabel) {
        JSONObject user = new JSONObject();
        user.put("name", name);
        user.put("email", email);
        user.put("password", password);

        if (userType.equals("Patient")) {
            JSONUtils.writeToJSONFile("data/patients.json", user);
        } else if (userType.equals("Doctor")) {
            JSONUtils.writeToJSONFile("data/doctors.json", user);
        }

        messageLabel.setText("Sign-in successful!");
    }
}
