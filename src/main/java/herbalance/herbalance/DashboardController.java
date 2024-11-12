package herbalance.herbalance;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DashboardController extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    }

    @FXML
    private Pane sidepane;

    @FXML
    private MFXButton logoutButton;

    // ImageViews as buttons
    @FXML
    private ImageView dashboardIcon;

    @FXML
    private ImageView heartIcon;

    @FXML
    private ImageView bloodDropIcon;

    @FXML
    private ImageView signOutIcon;

    @FXML
    private ImageView workoutIcon;

    @FXML
    private ImageView mealPlanIcon;

    @FXML
    private ImageView userIcon;

    @FXML
    private ImageView notificationIcon;

    @FXML
    public void initialize() {
        // Set up click events for icons
        if (dashboardIcon != null) dashboardIcon.setOnMouseClicked(event -> handleDashboardClick());
        if (heartIcon != null) heartIcon.setOnMouseClicked(event -> handleHeartClick());
        if (bloodDropIcon != null) bloodDropIcon.setOnMouseClicked(event -> handleBloodDropClick());
        if (signOutIcon != null) signOutIcon.setOnMouseClicked(event -> handleSignOutClick());
        if (workoutIcon != null) workoutIcon.setOnMouseClicked(event -> handleWorkoutClick());
        if (mealPlanIcon != null) mealPlanIcon.setOnMouseClicked(event -> handleMealPlanClick());
        if (userIcon != null) userIcon.setOnMouseClicked(event -> handleUserClick());
        if (notificationIcon != null) notificationIcon.setOnMouseClicked(event -> handleNotificationClick());
    }

    // ImageView event handlers
    private void handleDashboardClick() {
        showAlert("Dashboard");
    }

    private void handleHeartClick() {
        showAlert("Heart");
    }

    private void handleBloodDropClick() {
        showAlert("Blood Drop");
    }

    private void handleSignOutClick() {
        showAlert("Sign Out");
    }

    private void handleWorkoutClick() {
        showAlert("Workout");
    }

    private void handleMealPlanClick() {
        showAlert("Meal Plan");
    }

    private void handleUserClick() {
        showAlert("User");
    }

    private void handleNotificationClick() {
        showAlert("Notification");
    }

    @FXML
    private void logout(ActionEvent event) {

        Stage stage;

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().get() == ButtonType.OK) {

            stage = (Stage) logoutButton.getScene().getWindow();

            System.out.println("User logged out!");

            stage.close();
        }

    }

    // Display alert dialogs
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Click");
        alert.setHeaderText(null);
        alert.setContentText(message + " icon clicked!");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

}




