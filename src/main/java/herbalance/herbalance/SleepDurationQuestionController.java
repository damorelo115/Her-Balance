package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SleepDurationQuestionController {

    @FXML
    // Checkbox for less than 4 hours
    private CheckBox lessThanFourCheckBox;

    // Checkbox for 4-5 hours
    @FXML
    private CheckBox fourToFiveCheckBox;

    // Checkbox for 5-6 hours
    @FXML
    private CheckBox fiveToSixCheckBox;

    // Checkbox for 6-7 hours
    @FXML
    private CheckBox sixToSevenCheckBox;

    // Checkbox for 7-8 hours
    @FXML
    private CheckBox sevenToEightCheckBox;

    // Checkbox for more than 8 hours
    @FXML
    private CheckBox moreThanEightCheckBox;

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
        StringBuilder selectedSleep = new StringBuilder();

        if (lessThanFourCheckBox.isSelected()) {
            selectedSleep.append("- Less than 4 hours\n");
        }
        if (fourToFiveCheckBox.isSelected()) {
            selectedSleep.append("- 4-5 hours\n");
        }
        if (fiveToSixCheckBox.isSelected()) {
            selectedSleep.append("- 5-6 hours\n");
        }
        if (sixToSevenCheckBox.isSelected()) {
            selectedSleep.append("- 6-7 hours\n");
        }
        if (sevenToEightCheckBox.isSelected()) {
            selectedSleep.append("- 7-8 hours\n");
        }
        if (moreThanEightCheckBox.isSelected()) {
            selectedSleep.append("- More than 8 hours\n");
        }

        // Checking if no sleep duration is selected
        if (selectedSleep.toString().equals("Selected Sleep Duration:\n")) {
            selectedSleep.append("No duration selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);

        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);

        }
        // Printing selected sleep duration
        System.out.println(selectedSleep); // Needs to be changed to save into a file / database
    }
   // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            DietQuestion.loadDietQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
