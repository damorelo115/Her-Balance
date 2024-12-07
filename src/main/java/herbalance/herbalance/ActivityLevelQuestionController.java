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

public class ActivityLevelQuestionController {

    @FXML
    private CheckBox sedentaryCheckBox;

    @FXML
    private CheckBox moderatelyActiveCheckBox;

    @FXML
    private CheckBox highlyActiveCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Ensure the Next button is always visible and enabled
        nextButton.setVisible(true);
        nextButton.setDisable(false);
    }

    @FXML
    protected void onNextButtonClick() {
        // Retrieve user details from Main.theUser
        String userEmail = Main.theUser.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            // Gather selected activity levels
            Map<String, Boolean> activityLevelData = new HashMap<>();
            activityLevelData.put("Sedentary", sedentaryCheckBox.isSelected());
            activityLevelData.put("Moderately Active", moderatelyActiveCheckBox.isSelected());
            activityLevelData.put("Highly Active", highlyActiveCheckBox.isSelected());

            // Save activity level data to Firestore
            saveActivityLevelToFirestore(userEmail, activityLevelData);

            // Navigate to the next scene
            try {
                Stage stage = (Stage) nextButton.getScene().getWindow();
                MotivateQuestion.loadMotivateQuestionScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("User email is not available. Please log in again.");
        }
    }

    private void saveActivityLevelToFirestore(String email, Map<String, Boolean> activityLevelData) {
        Firestore db = Main.fstore;

        // Save the activity level data under the user's document
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("ActivityLevel").set(activityLevelData);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Activity level data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving activity level data: " + e.getMessage());
        }
    }

    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            WellnessFocusQuestion.loadWellnessFocusQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

