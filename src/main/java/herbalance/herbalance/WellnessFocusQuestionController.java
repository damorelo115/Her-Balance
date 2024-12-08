package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class WellnessFocusQuestionController {

    @FXML
    private CheckBox stressCheckBox;

    @FXML
    private CheckBox physicalActivityCheckBox;

    @FXML
    private CheckBox sleepCheckBox;

    @FXML
    private CheckBox reproductiveHealthCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Ensure the Next button is visible and disabled by default
        nextButton.setVisible(true);
        nextButton.setDisable(true);

        // Add listeners to the checkboxes to monitor state changes
        addCheckboxListeners();
    }

    private void addCheckboxListeners() {
        // Add listeners to each checkbox to call updateNextButtonState on state change
        stressCheckBox.setOnAction(e -> updateNextButtonState());
        physicalActivityCheckBox.setOnAction(e -> updateNextButtonState());
        sleepCheckBox.setOnAction(e -> updateNextButtonState());
        reproductiveHealthCheckBox.setOnAction(e -> updateNextButtonState());
    }

    private void updateNextButtonState() {
        // Enable the Next button if any checkbox is selected
        boolean isAnySelected = stressCheckBox.isSelected()
                || physicalActivityCheckBox.isSelected()
                || sleepCheckBox.isSelected()
                || reproductiveHealthCheckBox.isSelected();

        nextButton.setDisable(!isAnySelected); // Disable if none are selected
        nextButton.setVisible(true); // Ensure visibility
    }

    @FXML
    protected void onNextButtonClick() {
        // Retrieve user details from Main.theUser
        String userEmail = Main.theUser.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            // Gather selected wellness focus options
            Map<String, Boolean> wellnessFocusData = new HashMap<>();
            wellnessFocusData.put("Stress Management", stressCheckBox.isSelected());
            wellnessFocusData.put("Physical Activity", physicalActivityCheckBox.isSelected());
            wellnessFocusData.put("Sleep", sleepCheckBox.isSelected());
            wellnessFocusData.put("Reproductive Health", reproductiveHealthCheckBox.isSelected());

            // Save wellness focus data to Firestore
            saveWellnessFocusToFirestore(userEmail, wellnessFocusData);

            // Navigate to the next scene
            try {
                Stage stage = (Stage) nextButton.getScene().getWindow();
                ActivityLevelQuestion.loadActivityLevelQuestionScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("User email is not available. Please log in again.");
        }
    }

    private void saveWellnessFocusToFirestore(String email, Map<String, Boolean> wellnessFocusData) {
        Firestore db = Main.fstore;

        // Save the wellness focus data under the user's document
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("WellnessFocus").set(wellnessFocusData);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Wellness focus data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving wellness focus data: " + e.getMessage());
        }
    }

    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            WellnessGoalsQuestion.loadWellnessGoalsQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








