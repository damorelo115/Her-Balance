package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RecommendQuestionController {

    @FXML
    private RadioButton yesRadioButton;

    @FXML
    private RadioButton noRadioButton;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Ensure the Next button is always visible and enabled
        nextButton.setVisible(true);
        nextButton.setDisable(false);

        // Group the radio buttons into a ToggleGroup
        ToggleGroup recommendGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(recommendGroup);
        noRadioButton.setToggleGroup(recommendGroup);
    }

    @FXML
    protected void onNextButtonClick() {
        // Retrieve user details from Main.theUser
        String userEmail = Main.theUser.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            // Gather the selected recommendation option
            Map<String, Boolean> recommendData = new HashMap<>();
            recommendData.put("Personalized Recommendations", yesRadioButton.isSelected());
            recommendData.put("No Recommendations", noRadioButton.isSelected());

            // Save recommendation data to Firestore
            saveRecommendDataToFirestore(userEmail, recommendData);

            // Navigate to the next scene
            try {
                Stage stage = (Stage) nextButton.getScene().getWindow();
                RemindersQuestion.loadRemindersQuestionScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("User email is not available. Please log in again.");
        }
    }

    private void saveRecommendDataToFirestore(String email, Map<String, Boolean> recommendData) {
        Firestore db = Main.fstore;

        // Save the recommendation data under the user's document
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("Recommendations").set(recommendData);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Recommendation data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving recommendation data: " + e.getMessage());
        }
    }

    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            MotivateQuestion.loadMotivateQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

