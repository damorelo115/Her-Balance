package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageStressQuestionController {

    @FXML
    // Checkbox for meditation
    private CheckBox meditationCheckBox;

    // Checkbox for exercise
    @FXML
    private CheckBox exerciseCheckBox;

    // Checkbox for therapy
    @FXML
    private CheckBox therapyCheckBox;

    // Checkbox for none of the above
    @FXML
    private CheckBox noneCheckBox;

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
        StringBuilder selectedManageStress = new StringBuilder();

        if (meditationCheckBox.isSelected()) {
            selectedManageStress.append("- Meditation\n");
        }
        if (exerciseCheckBox.isSelected()) {
            selectedManageStress.append("- Exercise\n");
        }
        if (therapyCheckBox.isSelected()) {
            selectedManageStress.append("- Therapy\n");
        }
        if (noneCheckBox.isSelected()) {
            selectedManageStress.append("- None\n");
        }

        // Checking if no goals are selected
        if (selectedManageStress.toString().equals("Selected Stress Management:\n")) {
            selectedManageStress.append("No stress management option selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected stress level
        System.out.println(selectedManageStress); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            ActivitiesQuestion.loadActivitiesQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}