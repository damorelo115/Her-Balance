package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ActivityLevelController {

    @FXML
    // Checkbox for sedentary option
    private CheckBox sedentaryCheckBox;

    // Checkbox for moderately active option
    @FXML
    private CheckBox moderatelyActiveCheckBox;

    // Checkbox for highly active option
    @FXML
    private CheckBox highlyActiveCheckBox;

    // Submit Button
    @FXML
    private Button submitButton;

    // Next Button
    @FXML
    private Button nextButton;

    // Method triggered when Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        // Declaring and initializing a StringBuilder to store selected goals
        StringBuilder selectedActivity = new StringBuilder();

        if (sedentaryCheckBox.isSelected()) {
            selectedActivity.append("- Sedentary\n");
        }
        if (moderatelyActiveCheckBox.isSelected()) {
            selectedActivity.append("- Moderately Active\n");
        }
        if (highlyActiveCheckBox.isSelected()) {
            selectedActivity.append("- Highly Active\n");
        }

        // Checking if no goals are selected
        if (selectedActivity.toString().equals("Selected Activity Levels:\n")) {
            selectedActivity.append("No level selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected goals
        System.out.println(selectedActivity); // Needs to be changed to save into a file / database
    }
    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            SleepDuration.loadSleepDurationScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
