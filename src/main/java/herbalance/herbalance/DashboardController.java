package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DashboardController {

    @FXML
    private Pane sidepane;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button periodTrackButton;

    @FXML
    private Button workoutButton;

    @FXML
    private Button mealPlanButton;

    @FXML
    private Button wellnessTipButton;

    @FXML
    private Button affirmationButton;

    @FXML
    private TextArea affirmationTextArea;

    @FXML
    private TextArea wellnessTextArea;

    @FXML
    private Label userlabel;

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
    public void initialize() throws ExecutionException, InterruptedException {
        // Set up click events for icons
        if (dashboardIcon != null) dashboardIcon.setOnMouseClicked(event -> handleDashboardClick());
        if (heartIcon != null) heartIcon.setOnMouseClicked(event -> handleHeartClick());
        if (bloodDropIcon != null) bloodDropIcon.setOnMouseClicked(event -> handleBloodDropClick());
        if (signOutIcon != null) signOutIcon.setOnMouseClicked(event -> handleSignOutClick());
        if (workoutIcon != null) workoutIcon.setOnMouseClicked(event -> handleWorkoutClick());
        if (mealPlanIcon != null) mealPlanIcon.setOnMouseClicked(event -> handleMealPlanClick());
        if (userIcon != null) userIcon.setOnMouseClicked(event -> handleUserClick());
        if (notificationIcon != null) notificationIcon.setOnMouseClicked(event -> handleNotificationClick());


        if (Main.theUser.getUserEmail() != null) {

            fetchFirstName();
        }

    }

    // This method searches Firestore for the user's first name based on the email used to log in
    private void fetchFirstName() throws ExecutionException, InterruptedException {

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = Main.fstore.collection("Users")
                .whereEqualTo("Email", Main.theUser.getUserEmail())
                .get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;


        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {

                    String firstname = document.getString("FirstName");

                    userlabel.setText("Hi, " + firstname + "!");

                }

            } else {

                userlabel.setText("User not found");
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

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

    // This event is called to log the user out of the application and returns the user to the login screen
    @FXML
    private void logout(ActionEvent event) throws IOException {

        Stage stage;

        Alert alert = new Alert(AlertType.CONFIRMATION);
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

    // Display alert dialogs
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Click");
        alert.setHeaderText(null);
        alert.setContentText(message + " icon clicked!");
        alert.showAndWait();
    }

    @FXML
    protected void dashboardButtonClick() {

        try {

            Stage stage = (Stage) dashboardButton.getScene().getWindow();

            Dashboard.loadDashboardScene();

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
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void workoutButtonClick() {

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

    // Method that uses an API to fetch a daily affirmation that the user can read
    @FXML
    private void fetchAffirmation(ActionEvent event) throws IOException, InterruptedException {

        affirmationButton.setDisable(true);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://positivity-tips.p.rapidapi.com/api/positivity/affirmation"))
                    .header("x-rapidapi-key", "4ab2742280msh3a57872a2958f31p11c716jsnbfd1d0396e65")
                    .header("x-rapidapi-host", "positivity-tips.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            String affirmationResponse = response.body().replaceAll("[{}]", "");

            affirmationTextArea.setText(affirmationResponse + "\n");
            affirmationTextArea.setEditable(false);
            affirmationTextArea.setFont(new Font("Poppins", 14));
            affirmationTextArea.setWrapText(true);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        finally {

            affirmationButton.setDisable(false);

        }

    }

    // Method that uses an API to fetch a wellness tip that the user can read
    @FXML
    private void fetchWellnessTip(ActionEvent event) throws IOException, InterruptedException {

        wellnessTipButton.setDisable(true);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://positivity-tips.p.rapidapi.com/api/positivity/wellness"))
                    .header("x-rapidapi-key", "4ab2742280msh3a57872a2958f31p11c716jsnbfd1d0396e65")
                    .header("x-rapidapi-host", "positivity-tips.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            String wellnessResponse = response.body().replaceAll("[{}]", "");

            wellnessTextArea.setText(wellnessResponse + "\n");
            wellnessTextArea.setEditable(false);
            wellnessTextArea.setFont(new Font("Poppins", 12));
        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {

            wellnessTipButton.setDisable(false);
        }

    }

}