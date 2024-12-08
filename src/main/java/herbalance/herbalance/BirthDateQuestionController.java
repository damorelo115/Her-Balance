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
                validationLabel.setText(""); // Clear validation message when a date is selected
            }
        });

        // Disable the Next button initially
        nextButton.setDisable(true);

        // Enable the Next button only when a valid date is selected
        birthDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            nextButton.setDisable(newValue == null); // Disable if no date is selected
        });
    }

    @FXML
    protected void onNextButtonClick() {
        // Validate the DatePicker selection
        if (birthDatePicker.getValue() == null) {
            validationLabel.setText("Please select a birthdate.");
            return; // Stop further execution if validation fails
        }

        String birthDate = birthDatePicker.getValue().toString();

        // Retrieve user details from Main.theUser
        String userEmail = Main.theUser.getUserEmail();
        String password = Main.theUser.getPassword();
        String firstName = Main.theUser.getFirstName();

        if (userEmail != null && !userEmail.isEmpty()) {
            // Save user data to Firestore
            saveUserDataToFirestore(userEmail, firstName, password, birthDate);

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

    private void saveUserDataToFirestore(String email, String firstName, String password, String birthDate) {
        Firestore db = Main.fstore;

        // Data to save
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", firstName);
        data.put("password", password);
        data.put("birthDate", birthDate);

        // Save the user data to Firestore
        try {
            ApiFuture<WriteResult> future = db.collection("Users").document(email).set(data);

            // Wait for the operation to complete
            WriteResult result = future.get();
            System.out.println("User data saved successfully at: " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }
}



