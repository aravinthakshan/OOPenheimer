# Healthcare Management System

A JavaFX-based Healthcare Management System designed to manage appointments and user credentials for doctors and patients. This project allows doctors to view and manage their appointments and allows patients to book or reschedule appointments. It also includes secure login and sign-up features for both doctors and patients, with relevant user details stored in JSON files.

## Features
- **Login and Sign-Up System**: Users can sign up and log in as either a doctor or a patient. User credentials, including additional details, are securely stored in JSON files.
- **Doctor Dashboard**:
  - View specialization and contact information.
  - List of scheduled appointments.
  - Options to view patient details and reschedule appointments.
- **Patient Dashboard**:
  - View personal details such as age, sex, and blood group.
  - Book or reschedule appointments with available doctors.
  - View past appointment history.
- **Appointment Management**: Patients can book new appointments, and doctors can view and manage their appointments.

## Project Structure
The project is structured into several Java classes, each responsible for different components of the system:

### Java Classes
- **MainApp.java**: The entry point of the application. It displays the main menu for logging in as a doctor or patient.
- **LoginWindow.java**: Handles the login functionality for both doctors and patients.
- **SignUpWindow.java**: Allows users to sign up and input necessary details, which are stored in `credentials.json`.
- **DoctorDashboard.java**: Displays the doctor's dashboard, including appointments and personal details.
- **PatientDashboard.java**: Displays the patient's dashboard, including personal details, and provides options to book or reschedule appointments.
- **AppointmentBookingPage.java**: Manages the appointment booking and rescheduling process for patients.

### JSON Files
- **credentials.json**: Stores user credentials and additional details for both doctors and patients.
- **appointments.json**: Stores the appointment details, including doctor name, patient name, date, and time.

# **Shell Script**

**run.sh**: A shell script to simplify running the JavaFX application. This script sets up the necessary JavaFX libraries and launches the application.

## JSON File Structure
### `credentials.json`
```json
{
  "doctor": {
    "Dr. Smith": {
      "password": "docpass123",
      "specialization": "Cardiologist",
      "contact": "123-456-7890"
    },
    "Dr. Jones": {
      "password": "jonespass456",
      "specialization": "Dermatologist",
      "contact": "987-654-3210"
    }
  },
  "patient": {
    "p1": {
      "password": "password123",
      "age": "29",
      "sex": "Male",
      "bloodGroup": "B+"
    },
    "p2": {
      "password": "pass456",
      "age": "35",
      "sex": "Female",
      "bloodGroup": "O-"
    }
  }
}
```

### `appointments.json`
```json
[
  {
    "doctor": "Dr. Smith",
    "date": "2024-09-15",
    "summary": "Routine Checkup",
    "patient": "p1",
    "time": "10:00 AM"
  },
  {
    "doctor": "Dr. Jones",
    "date": "2024-08-20",
    "summary": "Follow-up",
    "patient": "p1",
    "time": "02:00 PM"
  },
  {
    "doctor": "Dr. Jones",
    "date": "2024-11-07",
    "patient": "p2",
    "time": "04:00 PM"
  }
]
```

## How to Run
1. **Set Up JavaFX**: Ensure you have JavaFX set up in your IDE. You may need to download JavaFX SDK and configure your IDE to include JavaFX libraries.
2. **Compile and Run**: Compile the project using your IDE or via command line using `javac`. Make sure to run the application with JavaFX runtime options.
3. **Launch the MainApp**: The main entry point is `MainApp.java`. Running this class will start the application and display the main menu.

## Prerequisites
- **Java 8 or later**: Ensure you have a compatible version of Java installed.
- **JavaFX**: Make sure JavaFX is properly set up in your environment.
