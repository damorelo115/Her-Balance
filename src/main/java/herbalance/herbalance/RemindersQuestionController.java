package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;


// import java.io.IOException;

public class RemindersQuestionController {

    // Checkbox for enabling notifications
    @FXML
    private CheckBox enableNotificationsCheckBox;

    // Checkbox for disabling notifications
    @FXML
    private CheckBox disableNotificationsCheckBox;

    // Submit Button
    @FXML
    private Button submitButton;

    // Next Button
  //  @FXML
  //  private Button nextButton;


    // Method triggered when Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        // Declaring and initializing a StringBuilder to store selected stress management methods
        StringBuilder selectedReminders = new StringBuilder();

        if (enableNotificationsCheckBox.isSelected()) {
            selectedReminders.append("- Enable Notifications\n");
        }
        if (disableNotificationsCheckBox.isSelected()) {
            selectedReminders.append("- Disable Notifications\n");
        }

        // Checking if no recommendation response is selected
        if (selectedReminders.toString().equals("Selected Notification Response:\n")) {
            selectedReminders.append("No notification option selected.");
            // Next button will be hidden if no goals are selected
         //   nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
   /*     } else {
            // Next button will be visible if goals are selected
              nextButton.setVisible(true); */
        }
        // Printing selected stress level
        System.out.println(selectedReminders); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
 /*   @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            RemindersQuestion.loadDashboardScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */
}