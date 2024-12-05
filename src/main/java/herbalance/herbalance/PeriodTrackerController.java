package herbalance.herbalance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PeriodTrackerController {

        private PeriodTracker tracker; // Tracker instance to store user data

        @FXML
        private Button dashboardButton;

        @FXML
        private ImageView dashboardIcon;

        @FXML
        private Button logoutButton;

        @FXML
        private Button mealPlanButton;

        @FXML
        private ImageView mealPlanIcon;

        @FXML
        private Button periodTrackButton;

        @FXML
        private Button predictCycle;

        @FXML
        private Text predictionText;

        @FXML
        private DatePicker previousCycleDatePicker;

        @FXML
        private TextField cycleLengthField;

        @FXML
        private TextField periodLengthField;

        @FXML
        private Pane sidepane;

        @FXML
        private ImageView signOutIcon;

        @FXML
        private Button workoutButton;

        @FXML
        private ImageView workoutIcon;


        @FXML
        void logout(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("You are about to logout!");
                alert.setContentText("Are you sure you want to logout?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                        Stage stage = (Stage) logoutButton.getScene().getWindow();
                        System.out.println("User logged out!");
                        stage.close();
                }
        }


        @FXML
        protected void dashboardButtonClick() throws IOException {
                navigateToScene("Dashboard", stage -> Dashboard.loadDashboardScene());
        }


        @FXML
        protected void periodButtonClick() {
                navigateToScene("Period Tracker", PeriodTracker::loadPeriodTrackerScene);
        }


        @FXML
        protected void workoutButtonClick() {
                navigateToScene("Fitness Tracker", Fitness::loadFitnessTrackerScene);
        }


        @FXML
        protected void mealPlanButtonClick() {
                navigateToScene("Meal Planner", MealPlanner::loadMealPlannerScene);
        }


        @FXML
        protected void predictCycle(ActionEvent event) {
                try {

                        validateInputs();

                        LocalDate lastPeriodStartDate = previousCycleDatePicker.getValue();
                        int cycleLength = Integer.parseInt(cycleLengthField.getText());
                        int periodLength = Integer.parseInt(periodLengthField.getText());


                        if (tracker == null) {
                                tracker = new PeriodTracker(lastPeriodStartDate, cycleLength, periodLength);
                        } else {
                                tracker.updateTracker(lastPeriodStartDate, cycleLength, periodLength);
                        }


                        LocalDate nextStartDate = tracker.predictNextCycleStart();
                        LocalDate nextEndDate = tracker.predictNextCycleEnd();

                        predictionText.setText(
                                "Next Period Start: " + nextStartDate + "\nNext Period End: " + nextEndDate
                        );
                } catch (NumberFormatException e) {
                        predictionText.setText("Invalid input. Please enter numeric values for cycle and period lengths.");
                } catch (IllegalArgumentException e) {
                        predictionText.setText(e.getMessage());
                } catch (Exception e) {
                        predictionText.setText("An unexpected error occurred. Please try again.");
                }
        }


        private void validateInputs() {
                if (previousCycleDatePicker.getValue() == null) {
                        throw new IllegalArgumentException("Please select a previous cycle start date.");
                }
                if (cycleLengthField.getText().isEmpty() || periodLengthField.getText().isEmpty()) {
                        throw new IllegalArgumentException("Cycle Length and Period Length are required.");
                }
                int cycleLength = Integer.parseInt(cycleLengthField.getText());
                int periodLength = Integer.parseInt(periodLengthField.getText());
                if (cycleLength <= 0 || periodLength <= 0) {
                        throw new IllegalArgumentException("Cycle Length and Period Length must be positive numbers.");
                }
        }


        private void navigateToScene(String sceneName, SceneLoader loader) {
                try {
                        Stage stage = (Stage) dashboardButton.getScene().getWindow();
                        loader.loadScene(stage);
                } catch (IOException e) {
                        showError("Navigation Error", "Unable to load the " + sceneName + " scene.");
                }
        }


        private void showError(String title, String message) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }

        @FunctionalInterface
        private interface SceneLoader {
                void loadScene(Stage stage) throws IOException;
        }
}