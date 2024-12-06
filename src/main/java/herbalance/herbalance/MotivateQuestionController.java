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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MotivateQuestionController {

    @FXML
    private CheckBox personalCheckBox;

    @FXML
    private CheckBox mentalCheckBox;

    @FXML
    private CheckBox physicalCheckBox;

    @FXML
    private CheckBox energyCheckBox;

    @FXML
    private CheckBox stressCheckBox;

    @FXML
    private CheckBox disciplineCheckBox;

    @FXML
    private CheckBox longHealthCheckBox;

    @FXML
    private CheckBox confidenceCheckBox;

    @FXML
    private CheckBox noneCheckBox;

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
            // Gather selected motivations
            Map<String, Boolean> motivationData = new HashMap<>();
            motivationData.put("Personal Growth", personalCheckBox.isSelected());
            motivationData.put("Mental Well-being", mentalCheckBox.isSelected());
            motivationData.put("Improved Physical Health", physicalCheckBox.isSelected());
            motivationData.put("Increased Energy Levels", energyCheckBox.isSelected());
            motivationData.put("Stress Relief", stressCheckBox.isSelected());
            motivationData.put("Building Self-discipline", disciplineCheckBox.isSelected());
            motivationData.put("Long-term Health", longHealthCheckBox.isSelected());
            motivationData.put("Confidence Boost", confidenceCheckBox.isSelected());
            motivationData.put("None of the Above", noneCheckBox.isSelected());

            // Save motivation data to Firestore
            saveMotivationDataToFirestore(userEmail, motivationData);

            // Navigate to the next scene
            try {
                Stage stage = (Stage) nextButton.getScene().getWindow();
                RecommendQuestion.loadRecommendQuestionScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("User email is not available. Please log in again.");
        }
    }

    private void saveMotivationDataToFirestore(String email, Map<String, Boolean> motivationData) {
        Firestore db = Main.fstore;

        // Save the motivation data under the user's document
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("Motivation").set(motivationData);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Motivation data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving motivation data: " + e.getMessage());
        }
    }

    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            ActivityLevelQuestion.loadActivityLevelQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



