package models;

public class Patient {
    private String name;
    private String email;
    private String password;
    private String medicalHistory;
    private String emergencyContact;

    // Constructors, Getters, and Setters
    public Patient(String name, String email, String password, String medicalHistory, String emergencyContact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.medicalHistory = medicalHistory;
        this.emergencyContact = emergencyContact;
    }

    // Getters and setters
    // ...
}
