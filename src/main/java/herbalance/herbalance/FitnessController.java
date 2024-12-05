package herbalance.herbalance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FitnessController {

    @FXML
    private ImageView bloodDropIcon;

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
            stage.close();
        }

        catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void periodButtonClick() throws IOException {

        try {
            Stage stage = (Stage) periodTrackButton.getScene().getWindow();

            PeriodTracker.loadPeriodTrackerScene(stage);

            stage.close();
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

        @FXML
        protected void workoutButtonClick() throws IOException {

            try {
                Stage stage = (Stage) workoutButton.getScene().getWindow();

                Fitness.loadFitnessTrackerScene(stage);

            }

            catch (IOException e) {

                throw new RuntimeException(e);
            }


        }

    @FXML
    protected void mealPlanButtonClick() throws IOException {

        try {

            Stage stage = (Stage) mealPlanButton.getScene().getWindow();

            MealPlanner.loadMealPlannerScene(stage);
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }

    }


}
