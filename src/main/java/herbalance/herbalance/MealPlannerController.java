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

public class MealPlannerController implements Initializable {

    @FXML
    private ComboBox<String> dayOptions;

    @FXML
    private ComboBox<String> breakfastOptions;

    @FXML
    private ComboBox<String> lunchOptions;

    @FXML
    private ComboBox<String> dinnerOptions;

    @FXML
    private TextArea weeklyPlanView;

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
    private Button viewPlanButton;

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

    // Method to log the user out of the application
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

    // Loads the Fitness Tracker scene
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

    // Initializes day options and meal choices
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> weeklymealPlan = FXCollections.observableArrayList();

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] breakfastmeals = {"Oatmeal", "Yogurt", "Avocado Toast", "Raspberry Protein Muffins", "Egg White Frittata"};
        String[] lunchmeals = {"Chicken Salad", "Lentil Soup", "Roasted Chickpea Wrap", "Salmon Bowl", "Turkey & Cheese Panini"};
        String[] dinnermeals = {"Chicken Tacos", "Oven-Baked Salmon", "Coconut Curry With Rice", "Vegetable Lasagna", "Penne Pasta with Pesto Sauce"};

        dayOptions.getItems().setAll(days);
        breakfastOptions.getItems().setAll(breakfastmeals);
        lunchOptions.getItems().setAll(lunchmeals);
        dinnerOptions.getItems().setAll(dinnermeals);

        weeklyPlanView.setWrapText(true);
        weeklyPlanView.setEditable(false);

    }

    // This method allow the user to choose a weekday and three meal options to add to a weekly meal plan. The chosen options
    // will be added to the document of the user in a collection
    @FXML
    private void addWeeklyPlan() {

        String day = dayOptions.getValue();
        String breakfast = breakfastOptions.getValue();
        String lunch = lunchOptions.getValue();
        String dinner = dinnerOptions.getValue();

        // Check if the options are not empty and add meal choices into the Meal Plan collection
        if (day != null && breakfast != null && lunch != null && dinner != null) {

            showAlert(Alert.AlertType.INFORMATION, "Meal plan has been added with the following information: " + "\n" +
                    "\n" + "Day: " + day + "\n" + "Breakfast: " + breakfast + "\n" + "Lunch: " + lunch + "\n" + "Dinner: " + dinner + "\n");

            DocumentReference docRef = Main.fstore.collection("Meal Plans").document(Main.theUser.getUserEmail());

            Map<String, String> mealchoices = new HashMap<>();

            mealchoices.put("Breakfast", breakfast);
            mealchoices.put("Lunch", lunch);
            mealchoices.put("Dinner", dinner);

            // asynchronously update doc, create the document if missing
            Map<String, Object> update = new HashMap<>();
            update.put(day, mealchoices);

            ApiFuture<WriteResult> writeResult = Main.fstore.collection("Meal Plans").document(Main.theUser.getUserEmail()).set(update, SetOptions.merge());

        } else {

            showAlert(Alert.AlertType.ERROR, "Please enter your specified meal plan!");
        }

        // Day and meal choices are cleared after being picked
        dayOptions.getSelectionModel().clearSelection();
        breakfastOptions.getSelectionModel().clearSelection();
        lunchOptions.getSelectionModel().clearSelection();
        dinnerOptions.getSelectionModel().clearSelection();

    }

    // Users will be able to view their curated weekly meal plan
    @FXML
    private void viewWeeklyPlan() throws ExecutionException, InterruptedException {

        //asynchronously retrieve all documents
        DocumentReference docRef = Main.fstore.collection("Meal Plans").document(Main.theUser.getUserEmail());
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = Main.fstore.collection("Meal Plans").get();

        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {

                    weeklyPlanView.setText(document.getData() + "\n" + "\n"  );

                }
            }

            else {
                weeklyPlanView.setText("No meal plan found");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

        // showAlert method to display alerts to the user
        private void showAlert (Alert.AlertType alertType, String message){

            Alert alert = new Alert(alertType);
            alert.setTitle(alert.getTitle());
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.getDialogPane().setStyle("-fx-font-size: 16px;");
            alert.show();
        }

    }