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

        private PeriodTracker tracker;

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
                Stage stage = (Stage) dashboardButton.getScene().getWindow();
                Dashboard.loadDashboardScene();
        }

        @FXML
        protected void periodButtonClick() {
                try {
                        Stage stage = (Stage) periodTrackButton.getScene().getWindow();
                        PeriodTracker.loadPeriodTrackerScene(stage);
                } catch (IOException e) {
                        showError("Navigation Error", "Unable to load the period tracker.");
                }
        }

        @FXML
        protected void workoutButtonClick() {
                try {
                        Stage stage = (Stage) workoutButton.getScene().getWindow();
                        Fitness.loadFitnessTrackerScene(stage);
                } catch (IOException e) {
                        showError("Navigation Error", "Unable to load the fitness tracker.");
                }
        }

        @FXML
        protected void mealPlanButtonClick() {
                try {
                        Stage stage = (Stage) mealPlanButton.getScene().getWindow();
                        MealPlanner.loadMealPlannerScene(stage);
                } catch (IOException e) {
                        showError("Navigation Error", "Unable to load the meal planner.");
                }
        }

        @FXML
        protected void predictCycle(ActionEvent event) {
                try {
                        // Check that required fields are filled
                        if (previousCycleDatePicker.getValue() == null) {
                                throw new IllegalArgumentException("Please select the previous cycle date.");
                        }

                        // Get user input for previous cycle date
                        LocalDate lastPeriodStartDate = previousCycleDatePicker.getValue();

                        // Predict next cycle start date (28 days after previous cycle date)
                        LocalDate nextCycleStartDate = lastPeriodStartDate.plusDays(28);

                        // Display the prediction
                        predictionText.setText("Next Predicted Cycle Start Date: " + nextCycleStartDate);

                } catch (IllegalArgumentException e) {
                        // Display error for invalid inputs
                        predictionText.setText(e.getMessage());
                } catch (Exception e) {
                        // Handle unexpected errors
                        predictionText.setText("An unexpected error occurred. Please try again.");
                }
        }

        private void showError(String title, String message) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }
}