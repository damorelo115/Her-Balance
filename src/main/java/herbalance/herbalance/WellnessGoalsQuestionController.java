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

public class WellnessGoalsQuestionController {

    @FXML
    private CheckBox fitnessCheckBox;

    @FXML
    private CheckBox mentalHealthCheckBox;

    @FXML
    private CheckBox nutritionCheckBox;

    @FXML
    private CheckBox hormonalBalanceCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Ensure the Next button is visible and disabled by default
        nextButton.setVisible(true);
        nextButton.setDisable(true);

        // Add listeners to the checkboxes
        addCheckboxListeners();
    }

    private void addCheckboxListeners() {
        fitnessCheckBox.setOnAction(e -> updateNextButtonState());
        mentalHealthCheckBox.setOnAction(e -> updateNextButtonState());
        nutritionCheckBox.setOnAction(e -> updateNextButtonState());
        hormonalBalanceCheckBox.setOnAction(e -> updateNextButtonState());
    }

    private void updateNextButtonState() {
        // Enable the Next button if any checkbox is selected
        boolean isAnySelected = fitnessCheckBox.isSelected()
                || mentalHealthCheckBox.isSelected()
                || nutritionCheckBox.isSelected()
                || hormonalBalanceCheckBox.isSelected();

        nextButton.setDisable(!isAnySelected);
        nextButton.setVisible(true); // Ensure visibility
    }

    @FXML
    protected void onNextButtonClick() {
        // Retrieve user details from Main.theUser
        String userEmail = Main.theUser.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            // Gather selected wellness goals
            Map<String, Boolean> wellnessGoalsData = new HashMap<>();
            wellnessGoalsData.put("Fitness", fitnessCheckBox.isSelected());
            wellnessGoalsData.put("Mental Health", mentalHealthCheckBox.isSelected());
            wellnessGoalsData.put("Nutrition", nutritionCheckBox.isSelected());
            wellnessGoalsData.put("Hormonal Balance", hormonalBalanceCheckBox.isSelected());

            // Save wellness goals data to Firestore
            saveWellnessGoalsToFirestore(userEmail, wellnessGoalsData);

            // Navigate to the next scene
            try {
                Stage stage = (Stage) nextButton.getScene().getWindow();
                WellnessFocusQuestion.loadWellnessFocusQuestionScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("User email is not available. Please log in again.");
        }
    }

    private void saveWellnessGoalsToFirestore(String email, Map<String, Boolean> wellnessGoalsData) {
        Firestore db = Main.fstore;

        // Save the wellness goals data under the user's document
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("WellnessGoals").set(wellnessGoalsData);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Wellness goals data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving wellness goals data: " + e.getMessage());
        }
    }

    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            BirthDateQuestion.loadBirthDateQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


