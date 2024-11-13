package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RemindersQuestionController {

    @FXML
    private CheckBox enableNotificationsCheckBox;

    @FXML
    private CheckBox disableNotificationsCheckBox;

    @FXML
    private Button submitButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    // Method called when the Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        StringBuilder selectedReminders = new StringBuilder();

        if (enableNotificationsCheckBox.isSelected()) {
            selectedReminders.append("- Enable Notifications\n");
        }
        if (disableNotificationsCheckBox.isSelected()) {
            selectedReminders.append("- Disable Notifications\n");
        }

        if (selectedReminders.toString().isEmpty()) {
            selectedReminders.append("No notification option selected.");
            if (signUpButton != null) {
                signUpButton.setVisible(false);
            }
            submitButton.setDisable(true);
        } else {
            if (signUpButton != null) {
                signUpButton.setVisible(true);
            }
        }

        System.out.println(selectedReminders);
    }

    // Method called when the Sign-Up button is clicked
    @FXML
    protected void onSignUpButtonClick() {
        // save theUser data to the database

        if (addUserSurveyData () == true) {
            Dashboard.loadDashboardScene();
        }
        else{
            showAlert(Alert.AlertType.ERROR, "Registration failed!");
        }
    }

    // This method is called when submitting survey data.
    // this method will save all the information about theUser to firestore
    public boolean addUserSurveyData() {

        DocumentReference docRef = Main.fstore.collection("Surveys").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("Email", Main.theUser.getUsername());
        data.put("FirstName", Main.theUser.getFirstName());

        try {
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
        }

        catch (Exception ex) {

            return false;
        }

        return true;

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

    private void showAlert (Alert.AlertType alertType, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(alert.getTitle());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


}
