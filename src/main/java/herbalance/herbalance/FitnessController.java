package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FitnessController implements Initializable {

    @FXML
    private ComboBox<String> dayOptions;

    @FXML
    private ComboBox<String> cardioOptions;

    @FXML
    private ComboBox<String> strengthOptions;

    @FXML
    private ComboBox<String> flexibilityOptions;

    @FXML
    private TextArea weeklyPlanView;

    @FXML
    private ImageView fitnessIcon;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button fitnessPlanButton;

    @FXML
    private Button periodTrackButton;

    @FXML
    private Button mealPlanButton;

    @FXML
    private Button viewPlanButton;

    @FXML
    private Pane sidepane;

    @FXML
    private Button workoutButton;

    // Log the user out of the application
    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) logoutButton.getScene().getWindow();
            System.out.println("User logged out!");
            stage.close();
            UserLogin.loadUserLoginScene(stage);
        }
    }

    // Loads the Dashboard scene
    @FXML
    protected void dashboardButtonClick() {
        Stage stage = (Stage) dashboardButton.getScene().getWindow();
        stage.close();
        Dashboard.loadDashboardScene();
    }

    // Loads the Period Tracker scene
    @FXML
    protected void periodButtonClick() throws IOException {

        try {
            Stage stage = (Stage) periodTrackButton.getScene().getWindow();

            PeriodTracker.loadPeriodTrackerScene(stage);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }


    }

    // Loads the FitnessTracker Tracker scene
    @FXML
    protected void workoutButtonClick() throws IOException {

        try {

            Stage stage = (Stage) workoutButton.getScene().getWindow();

            Fitness.loadFitnessTrackerScene(stage);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    // Loads the Meal Planner scene
    @FXML
    protected void mealPlanButtonClick() throws IOException {

        try {

            Stage stage = (Stage) mealPlanButton.getScene().getWindow();

            MealPlanner.loadMealPlannerScene(stage);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    // Initializes day options and workout choices
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> weeklyFitnessPlan = FXCollections.observableArrayList();

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] cardioWorkouts = {"Running", "Cycling", "Jump Rope", "Swimming", "Rowing"};
        String[] strengthWorkouts = {"Push-Ups", "Deadlifts", "Squats", "Bench Press", "Pull-Ups"};
        String[] flexibilityWorkouts = {"Yoga", "Stretching", "Pilates", "Tai Chi", "Foam Rolling"};

        dayOptions.getItems().setAll(days);
        cardioOptions.getItems().setAll(cardioWorkouts);
        strengthOptions.getItems().setAll(strengthWorkouts);
        flexibilityOptions.getItems().setAll(flexibilityWorkouts);

        weeklyPlanView.setWrapText(true);
        weeklyPlanView.setEditable(false);
    }

    // Add a weekly fitness plan
    @FXML
    private void addWeeklyPlan() {
        String day = dayOptions.getValue();
        String cardio = cardioOptions.getValue();
        String strength = strengthOptions.getValue();
        String flexibility = flexibilityOptions.getValue();

        if (day != null && cardio != null && strength != null && flexibility != null) {
            showAlert(Alert.AlertType.INFORMATION, "FitnessTracker plan added with the following information:\n" +
                    "\nDay: " + day +
                    "\nCardio: " + cardio +
                    "\nStrength: " + strength +
                    "\nFlexibility: " + flexibility);

            DocumentReference docRef = Main.fstore.collection("FitnessTracker Plans").document(Main.theUser.getUserEmail());

            Map<String, String> workoutChoices = new HashMap<>();
            workoutChoices.put("Cardio", cardio);
            workoutChoices.put("Strength", strength);
            workoutChoices.put("Flexibility", flexibility);

            Map<String, Object> update = new HashMap<>();
            update.put(day, workoutChoices);

            ApiFuture<WriteResult> writeResult = docRef.set(update, SetOptions.merge());
        } else {
            showAlert(Alert.AlertType.ERROR, "Please complete all fields before submitting!");
        }

        dayOptions.getSelectionModel().clearSelection();
        cardioOptions.getSelectionModel().clearSelection();
        strengthOptions.getSelectionModel().clearSelection();
        flexibilityOptions.getSelectionModel().clearSelection();
    }

    // View the weekly fitness plan
    @FXML
    private void viewWeeklyPlan() throws ExecutionException, InterruptedException {
        DocumentReference docRef = Main.fstore.collection("FitnessTracker Plans").document(Main.theUser.getUserEmail());
        ApiFuture<QuerySnapshot> future = Main.fstore.collection("FitnessTracker Plans").get();

        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    weeklyPlanView.setText(document.getData() + "\n\n");
                }
            } else {
                weeklyPlanView.setText("No fitness plan found");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    // Show alerts to the user
    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alert.getTitle());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-font-size: 16px;");
        alert.show();
    }
}
