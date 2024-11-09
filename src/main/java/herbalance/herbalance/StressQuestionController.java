package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StressQuestionController {

    @FXML
    // Checkbox for low stress
    private CheckBox lowCheckBox;

    // Checkbox for moderate stress
    @FXML
    private CheckBox moderateCheckBox;

    // Checkbox for high stress
    @FXML
    private CheckBox highCheckBox;

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
        // Declaring and initializing a StringBuilder to store selected goals
        StringBuilder selectedStress = new StringBuilder();

        if (lowCheckBox.isSelected()) {
            selectedStress.append("- Low\n");
        }
        if (moderateCheckBox.isSelected()) {
            selectedStress.append("- Moderate\n");
        }
        if (highCheckBox.isSelected()) {
            selectedStress.append("- High\n");
        }

        // Checking if no goals are selected
        if (selectedStress.toString().equals("Selected Stress Level:\n")) {
            selectedStress.append("No level selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected stress level
        System.out.println(selectedStress); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            ManageStressQuestion.loadManageStressQuestionScene(stage);
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