package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BirthDateQuestionController {

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label validationLabel;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Monitor changes to the birthDatePicker
        birthDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                birthDateLabel.setText("Your birthdate: " + newValue.toString());
            } else {
                validationLabel.setText("Please select a birthdate.");

            }
        });
    }

    @FXML
    protected void onNextButtonClick() {
        if (birthDatePicker.getValue() != null) {
            String birthDate = birthDatePicker.getValue().toString();

            // Retrieve the user's email from Main.theUser
            String userEmail = Main.theUser.getUserEmail();

            if (userEmail != null && !userEmail.isEmpty()) {
                // Save birthdate to Firestore
                saveBirthDateToFirestore(userEmail, birthDate);

                // Navigate to the next scene
                try {
                    Stage stage = (Stage) nextButton.getScene().getWindow();
                    WellnessGoalsQuestion.loadWellnessGoalsQuestionScene(stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle case where email is not available
                validationLabel.setText("User email is not available. Please log in again.");

            }
        }
    }

    private void saveBirthDateToFirestore(String email, String birthDate) {
        Firestore db = Main.fstore;

        // Data to save
        Map<String, Object> data = new HashMap<>();
        data.put("birthDate", birthDate);

        // Save the birthdate to Firestore
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email)
                    .collection("Survey").document("BirthDate").set(data);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("Birthdate saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving birthdate: " + e.getMessage());
        }
    }
}


