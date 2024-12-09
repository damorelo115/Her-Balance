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
        private TextField cycleLengthField;

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
                if (cycleLengthField == null || periodLengthField == null || previousCycleDatePicker == null) {
                        predictionText.setText("Please enter all required inputs.");
                        return;
                }

                try {
                        validateInputs();

                        LocalDate lastPeriodStartDate = previousCycleDatePicker.getValue();
                        int cycleLength = Integer.parseInt(cycleLengthField.getText());
                        int periodLength = Integer.parseInt(periodLengthField.getText());

                        if (tracker == null) {
                                tracker = new PeriodTracker(lastPeriodStartDate, cycleLength);
                        }
                        tracker.setPeriodLength(periodLength);

                        LocalDate nextStartDate = tracker.predictNextCycleStart();
                        LocalDate nextEndDate = tracker.predictNextCycleEnd();
                        LocalDate ovulationDate = tracker.predictOvulation();

                        predictionText.setText(String.format(
                                "Predicted Next Period:\nStart Date: %s\nEnd Date: %s\nOvulation Date: %s",
                                nextStartDate, nextEndDate, ovulationDate
                        ));
                } catch (NumberFormatException e) {
                        predictionText.setText("Invalid input. Please enter numeric values for Cycle Length and Period Length.");
                } catch (IllegalArgumentException e) {
                        predictionText.setText(e.getMessage());
                }
        }

        private void validateInputs() {
                if (previousCycleDatePicker.getValue() == null) {
                        throw new IllegalArgumentException("Please select a previous cycle start date.");
                }
                if (cycleLengthField.getText().isEmpty()) {
                        throw new IllegalArgumentException("Cycle Length is required.");
                }
                if (periodLengthField.getText().isEmpty()) {
                        throw new IllegalArgumentException("Period Length is required.");
                }
                try {
                        int cycleLength = Integer.parseInt(cycleLengthField.getText());
                        int periodLength = Integer.parseInt(periodLengthField.getText());
                        if (cycleLength <= 0 || periodLength <= 0) {
                                throw new IllegalArgumentException("Cycle Length and Period Length must be positive numbers.");
                        }
                } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Cycle Length and Period Length must be numeric values.");
                }
        }
}


