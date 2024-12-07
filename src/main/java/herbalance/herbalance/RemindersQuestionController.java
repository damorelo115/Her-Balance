package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RemindersQuestionController {

    @FXML
    private RadioButton enableNotificationsRadioButton;

    @FXML
    private RadioButton disableNotificationsRadioButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // Ensure Submit and Sign-Up buttons are always visible and enabled
        submitButton.setDisable(false);
        if (signUpButton != null) {
            signUpButton.setVisible(true);
        }

        // Group the radio buttons into a ToggleGroup
        ToggleGroup notificationGroup = new ToggleGroup();
        enableNotificationsRadioButton.setToggleGroup(notificationGroup);
        disableNotificationsRadioButton.setToggleGroup(notificationGroup);
    }

    // Method called when the Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        // Retrieve user details from Main.theUser
        String userEmail = Main.theUser.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            // Gather the selected reminder option
            Map<String, Boolean> reminderData = new HashMap<>();
            reminderData.put("Enable Notifications", enableNotificationsRadioButton.isSelected());
            reminderData.put("Disable Notifications", disableNotificationsRadioButton.isSelected());

            // Save reminder data to Firestore
            saveReminderDataToFirestore(userEmail, reminderData);

            System.out.println("Reminder data submitted.");
        } else {
            showAlert(Alert.AlertType.ERROR, "User email is not available. Please log in again.");
        }
    }

    private void saveReminderDataToFirestore(String email, Map<String, Boolean> reminderData) {
        Firestore db = Main.fstore;

        // Save the reminder data under the user's document
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("Reminders").set(reminderData);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Reminder data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving reminder data: " + e.getMessage());
        }
    }

    // Method called when the Sign-Up button is clicked
    @FXML
    protected void onSignUpButtonClick() {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Dashboard.loadDashboardScene();
    }

    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            RecommendQuestion.loadRecommendQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
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


