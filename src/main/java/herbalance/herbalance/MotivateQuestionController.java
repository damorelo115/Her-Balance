package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MotivateQuestionController {

    // Checkbox for personal growth
    @FXML
    private CheckBox personalCheckBox;

    // Checkbox for mental well-being
    @FXML
    private CheckBox mentalCheckBox;

    // Checkbox for improved physical health
    @FXML
    private CheckBox physicalCheckBox;

    // Checkbox for increased energy levels
    @FXML
    private CheckBox energyCheckBox;

    // Checkbox for stress relief
    @FXML
    private CheckBox stressCheckBox;

    // Checkbox for building self-discipline
    @FXML
    private CheckBox disciplineCheckBox;

    // Checkbox for long-term health
    @FXML
    private CheckBox longHealthCheckBox;

    // Checkbox for confidence boost
    @FXML
    private CheckBox confidenceCheckBox;

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
        StringBuilder selectedMotivate = new StringBuilder();

        if (personalCheckBox.isSelected()) {
            selectedMotivate.append("- Personal Growth\n");
        }
        if (mentalCheckBox.isSelected()) {
            selectedMotivate.append("- Mental Well-Being\n");
        }
        if (physicalCheckBox.isSelected()) {
            selectedMotivate.append("- Improved Physical Health\n");
        }
        if (energyCheckBox.isSelected()) {
            selectedMotivate.append("- Increased Energy Levels\n");
        }
        if (stressCheckBox.isSelected()) {
            selectedMotivate.append("- Stress Relief\n");
        }
        if (disciplineCheckBox.isSelected()) {
            selectedMotivate.append("- Building Self-Discipline\n");
        }
        if (longHealthCheckBox.isSelected()) {
            selectedMotivate.append("- Long-Term Health\n");
        }
        if (confidenceCheckBox.isSelected()) {
            selectedMotivate.append("- Confidence Boost\n");
        }
        if (noneCheckBox.isSelected()) {
            selectedMotivate.append("- None of the above\n");
        }

        // Checking if no goals are selected
        if (selectedMotivate.toString().equals("Selected Wellness Motivations:\n")) {
            selectedMotivate.append("No motivation option selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected stress level
        System.out.println(selectedMotivate); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            RecommendQuestion.loadRecommendQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            ActivitiesQuestion.loadActivitiesQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
