package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;

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
}
