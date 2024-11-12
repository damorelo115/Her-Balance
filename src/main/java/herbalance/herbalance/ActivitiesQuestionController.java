package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ActivitiesQuestionController {

    @FXML
    // Checkbox for yoga
    private CheckBox yogaCheckBox;

    // Checkbox for strength training
    @FXML
    private CheckBox strengthCheckBox;

    // Checkbox for running
    @FXML
    private CheckBox runningCheckBox;

    // Checkbox for cycling
    @FXML
    private CheckBox cyclingCheckBox;

    // Checkbox for swimming
    @FXML
    private CheckBox swimmingCheckBox;

    // Checkbox for hiking
    @FXML
    private CheckBox hikingCheckBox;

    // Checkbox for none of the above
    @FXML
    private CheckBox noneCheckBox;

    // Back Button
    @FXML
    private Button backButton;

    // Submit Button
    @FXML
    private Button submitButton;

    // Next Button
    @FXML
    private Button nextButton;


    // Method triggered when Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        // Declaring and initializing a StringBuilder to store selected stress management methods
        StringBuilder selectedActivities = new StringBuilder();

        if (yogaCheckBox.isSelected()) {
            selectedActivities.append("- Yoga\n");
        }
        if (strengthCheckBox.isSelected()) {
            selectedActivities.append("- Strength\n");
        }
        if (runningCheckBox.isSelected()) {
            selectedActivities.append("- Running\n");
        }
        if (cyclingCheckBox.isSelected()) {
            selectedActivities.append("- Cycling\n");
        }
        if (swimmingCheckBox.isSelected()) {
            selectedActivities.append("- Swimming\n");
        }
        if (hikingCheckBox.isSelected()) {
            selectedActivities.append("- Hiking\n");
        }
        if (noneCheckBox.isSelected()) {
            selectedActivities.append("- None of the above\n");
        }

        // Checking if no goals are selected
        if (selectedActivities.toString().equals("Selected Physical Activities:\n")) {
            selectedActivities.append("No physical activity option selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected stress level
        System.out.println(selectedActivities); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            MotivateQuestion.loadMotivateQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            DietQuestion.loadDietQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
