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

        @FXML
        private DatePicker previousCycleDatePicker;

        @FXML
        private TextField periodLengthField;

        @FXML
        private Button cyclebutton;

        @FXML
        private Text predictionText;

        @FXML
        private Pane sidepane;

        @FXML
        private Button dashboardButton, logoutButton, periodTrackButton, workoutButton, mealPlanButton;

        @FXML
        private ImageView dashboardIcon, bloodDropIcon, workoutIcon, mealPlanIcon;

        private PeriodTracker tracker; // Instance to calculate cycle predictions

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
                Dashboard.loadDashboardScene(stage);
        }

        @FXML
        protected void periodButtonClick() throws IOException {
                Stage stage = (Stage) periodTrackButton.getScene().getWindow();
                PeriodTracker.loadPeriodTrackerScene(stage);
        }

        @FXML
        protected void workoutButtonClick() throws IOException {
                Stage stage = (Stage) workoutButton.getScene().getWindow();
                Fitness.loadFitnessTrackerScene(stage);
        }

        @FXML
        protected void mealPlanButtonClick() throws IOException {
                Stage stage = (Stage) mealPlanButton.getScene().getWindow();
                MealPlanner.loadMealPlannerScene(stage);
        }

        @FXML
        protected void predictCycle(ActionEvent event) {
                try {
                        validateInputs();

                        LocalDate lastPeriodStartDate = previousCycleDatePicker.getValue();
                        int periodLength = Integer.parseInt(periodLengthField.getText());

                        if (tracker == null) {
                                tracker = new PeriodTracker(lastPeriodStartDate, periodLength);
                        } else {
                                tracker.updateTracker(lastPeriodStartDate, periodLength);
                        }

                        LocalDate nextStartDate = tracker.predictNextCycleStart();
                        LocalDate nextEndDate = tracker.predictNextCycleEnd();

                        predictionText.setText("Next Period Start: " + nextStartDate + "\nNext Period End: " + nextEndDate);
                } catch (NumberFormatException e) {
                        predictionText.setText("Invalid input. Please enter numeric values.");
                } catch (IllegalArgumentException e) {
                        predictionText.setText(e.getMessage());
                }
        }

        private void validateInputs() {
                if (previousCycleDatePicker.getValue() == null) {
                        throw new IllegalArgumentException("Please select a previous cycle start date.");
                }
                if (periodLengthField.getText().isEmpty()) {
                        throw new IllegalArgumentException("Period Length is required.");
                }
                int periodLength = Integer.parseInt(periodLengthField.getText());
                if (periodLength <= 0) {
                        throw new IllegalArgumentException("Period Length must be positive.");
                }
        }
}
