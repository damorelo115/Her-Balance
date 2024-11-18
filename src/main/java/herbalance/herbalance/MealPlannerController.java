package herbalance.herbalance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MealPlannerController implements Initializable {

    @FXML
    private TableView<String> weeklyPlanView;

    @FXML
    private ComboBox<String> dayOptions;

    @FXML
    private ComboBox<String> breakfastOptions;

    @FXML
    private ComboBox<String> lunchOptions;

    @FXML
    private ComboBox<String> dinnerOptions;


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

    @FXML
    protected void dashboardButtonClick() {

        Stage stage = (Stage) dashboardButton.getScene().getWindow();

        stage.close();

        Dashboard.loadDashboardScene();


    }


    @FXML
    protected void periodButtonClick() throws IOException {

        try {
            Stage stage = (Stage) periodTrackButton.getScene().getWindow();

            PeriodTracker.loadPeriodTrackerScene(stage);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] days = {"Monday", "Tuesday", "Wednesday","Thursday", "Friday"};
        String[] breakfastmeals = {"Oatmeal", "Yogurt", "Avocado Toast", "Raspberry Protein Muffins", "Egg White Frittata"};
        String [] lunchmeals = {"Chicken Salad", "Lentil Soup", "Roasted Chickpea Wrap", "Salmon Bowl", "Turkey & Cheese Panini"};
        String [] dinnermeals = {"Chicken Tacos", "Oven-Baked Salmon", "Coconut Curry With Rice", "Vegetable Lasagna", "Penne Pasta with Pesto Sauce"};

        dayOptions.getItems().setAll(days);
       breakfastOptions.getItems().setAll(breakfastmeals);
       lunchOptions.getItems().setAll(lunchmeals);
       dinnerOptions.getItems().setAll(dinnermeals);

    }

}




