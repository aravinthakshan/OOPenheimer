package utils;

import org.json.JSONObject;
import java.util.Iterator;

public class Authentication {
    public static boolean authenticatePatient(String email, String password) {
        JSONObject patients = JSONUtils.readJSONFile("data/patients.json");
        Iterator<String> keys = patients.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject patient = patients.getJSONObject(key);
            if (patient.getString("email").equals(email) && patient.getString("password").equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean authenticateDoctor(String email, String password) {
        JSONObject doctors = JSONUtils.readJSONFile("data/doctors.json");
        Iterator<String> keys = doctors.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject doctor = doctors.getJSONObject(key);
            if (doctor.getString("email").equals(email) && doctor.getString("password").equals(password)) {
                return true;
            }
        }
        return false;
    }
}
