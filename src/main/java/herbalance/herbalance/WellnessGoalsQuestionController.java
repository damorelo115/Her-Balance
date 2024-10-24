package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WellnessGoalsQuestionController {

    @FXML
    // Checkbox for fitness goal
    private CheckBox fitnessCheckBox;

    // Checkbox for mental health goal
    @FXML
    private CheckBox mentalHealthCheckBox;

    // Checkbox for nutrition goal
    @FXML
    private CheckBox nutritionCheckBox;

    // Checkbox for hormonal balance goal
    @FXML
    private CheckBox hormonalBalanceCheckBox;

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
        StringBuilder selectedGoals = new StringBuilder();

        if (fitnessCheckBox.isSelected()) {
            selectedGoals.append("- Fitness\n");
        }
        if (mentalHealthCheckBox.isSelected()) {
            selectedGoals.append("- Mental Health\n");
        }
        if (nutritionCheckBox.isSelected()) {
            selectedGoals.append("- Nutrition\n");
        }
        if (hormonalBalanceCheckBox.isSelected()) {
            selectedGoals.append("- Hormonal Balance\n");
        }

        // Checking if no goals are selected
        if (selectedGoals.toString().equals("Selected Wellness Goals:\n")) {
            selectedGoals.append("No goals selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected goals
        System.out.println(selectedGoals); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            WellnessFocusQuestion.loadWellnessFocusQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


