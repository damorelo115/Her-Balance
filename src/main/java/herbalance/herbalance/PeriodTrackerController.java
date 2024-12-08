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
        private TextArea predictionBox;
        
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
                Stage stage;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Logout");
                alert.setHeaderText("You are about to logout!");
                alert.setContentText("Are you sure you want to logout?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                        stage = (Stage) logoutButton.getScene().getWindow();
                        System.out.println("User logged out!");
                        stage.close();
                }
        }


        @FXML
        protected void dashboardButtonClick() {
                try {
                        Stage stage = (Stage) dashboardButton.getScene().getWindow();
                        Dashboard.loadDashboardScene();
                } catch (Exception e) {
                        throw new RuntimeException(e);
                }
        }


        @FXML
        protected void periodButtonClick() throws IOException {
                try {
                        Stage stage = (Stage) periodTrackButton.getScene().getWindow();
                        PeriodTracker.loadPeriodTrackerScene(stage);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }


        @FXML
        protected void workoutButtonClick() throws IOException {
                try {
                        Stage stage = (Stage) workoutButton.getScene().getWindow();
                        Fitness.loadFitnessTrackerScene(stage);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }


        @FXML
        protected void mealPlanButtonClick() throws IOException {
                try {
                        Stage stage = (Stage) mealPlanButton.getScene().getWindow();
                        MealPlanner.loadMealPlannerScene(stage);
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }


        @FXML
        protected void predictCycle(ActionEvent event) {
                try {
                        if (previousCycleDatePicker.getValue() == null && cycleLengthField.getText().isEmpty()) {
                                showAlert(Alert.AlertType.ERROR,"Previous cycle date and cycle length must be filled.");
                        }

                        LocalDate lastPeriodStartDate = previousCycleDatePicker.getValue();
                        
                        int cycleLength = 28;

                        int daylength = Integer.parseInt(cycleLengthField.getText());

                        // Predict next cycle based on the given inputs
                        LocalDate nextCycleStartDate = lastPeriodStartDate.plusDays(cycleLength + 28);

                        predictionBox.setText("Next Period Start: " + nextCycleStartDate);

                } 
                
                catch (Exception e) {

                        predictionText.setText("Invalid input. Please check your entries.");
                }
        }
        private void showAlert(Alert.AlertType alertType, String message) {

                Alert alert = new Alert(alertType);
                alert.setTitle(alert.getTitle());
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.show();
        }
}