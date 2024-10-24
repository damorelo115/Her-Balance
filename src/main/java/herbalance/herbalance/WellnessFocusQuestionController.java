package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WellnessFocusQuestionController {

    @FXML
    // Checkbox for stress area
    private CheckBox stressCheckBox;

    // Checkbox for physical activity area
    @FXML
    private CheckBox physicalActivityCheckBox;

    // Checkbox for sleep area
    @FXML
    private CheckBox sleepCheckBox;

    // Checkbox for reproductive health area
    @FXML
    private CheckBox reproductiveHealthCheckBox;

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
        StringBuilder selectedAreas = new StringBuilder();

        if (stressCheckBox.isSelected()) {
            selectedAreas.append("- Stress\n");
        }
        if (physicalActivityCheckBox.isSelected()) {
            selectedAreas.append("- Physical Activity\n");
        }
        if (sleepCheckBox.isSelected()) {
            selectedAreas.append("- Sleep\n");
        }
        if (reproductiveHealthCheckBox.isSelected()) {
            selectedAreas.append("- Reproductive Health\n");
        }

        // Checking if no goals are selected
        if (selectedAreas.toString().equals("Selected Wellness Areas:\n")) {
            selectedAreas.append("No areas selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected goals
        System.out.println(selectedAreas); // Needs to be changed to save into a file / database
    }
    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            ActivityLevelQuestion.loadActivityLevelQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
