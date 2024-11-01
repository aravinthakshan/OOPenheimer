package views;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import controllers.DoctorDashboardController;

public class DoctorDashboardView {
    private VBox view;
    private Label welcomeLabel;
    private ListView<String> scheduleListView;

    public DoctorDashboardView() {
        welcomeLabel = new Label("Welcome, Doctor!");
        scheduleListView = new ListView<>();

        view = new VBox(10, welcomeLabel, scheduleListView);

        // Pass the required arguments to DoctorDashboardController
        DoctorDashboardController controller = new DoctorDashboardController(welcomeLabel, scheduleListView);
        controller.loadDoctorDetails("doctor@example.com"); // Example usage
    }

    public VBox getView() {
        return view;
    }
}
