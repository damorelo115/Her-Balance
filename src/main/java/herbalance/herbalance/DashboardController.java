package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class DashboardController {

    // dashboard buttons
    @FXML
    private Button Home;

    @FXML
    private Button Fitness;

    @FXML
    private Button MealPlan;

    @FXML
    private Button PeriodTracker;

    @FXML
    private Button Mood;

    @FXML
    private Button Settings;

    @FXML
    private Button Notification;

    @FXML
    public void initialize(){
        Home.setOnAction(event -> handleHomeButton());
        Fitness.setOnAction(event -> handleFitnessButton());
        MealPlan.setOnAction(event -> handleMealPlanButton());
        PeriodTracker.setOnAction(event -> handlePeriodTrackerButton());
        Mood.setOnAction(event -> handleMoodButton());
        Settings.setOnAction(event -> handleSettingsButton());
        Notification.setOnAction(event -> handleNotificationButton());
    }

    //Handle Dashboard buttons
    public void handleHomeButton(){

        showAlert("Home");
    }

    protected abstract void showAlert(String home);

    public void handleFitnessButton(){

        showAlert("Fitness");
    }
    public void handleMealPlanButton(){
        showAlert("Meal Plan");
    }
    public void handlePeriodTrackerButton(){

        showAlert("Period Tracker");
    }
    public void handleMoodButton(){

        showAlert("Mood");
    }
    public void handleSettingsButton(){

        showAlert("Settings");
    }
    public void handleNotificationButton(){

        showAlert("Notification");
    }

    //Display alert dialogs
    /*private void showAlert(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Click");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }*/

}
