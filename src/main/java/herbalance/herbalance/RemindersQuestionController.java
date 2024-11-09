package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;


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
            signUpButton.setVisible(false);
            submitButton.setDisable(true);
        } else {
            signUpButton.setVisible(true);
        }

        System.out.println(selectedReminders);
    }

    @FXML
    protected void onSignUpButtonClick() {
        Dashboard2.loadDashboardScene();
    }
}