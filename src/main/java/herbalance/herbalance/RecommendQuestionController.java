package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RecommendQuestionController {

    // Checkbox for yes selection
    @FXML
    private CheckBox yesCheckBox;

    // Checkbox for no selection
    @FXML
    private CheckBox noCheckBox;

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
        StringBuilder selectedRecommend = new StringBuilder();

        if (yesCheckBox.isSelected()) {
            selectedRecommend.append("- Yes\n");
        }
        if (noCheckBox.isSelected()) {
            selectedRecommend.append("- No\n");
        }

        // Checking if no recommendation response is selected
        if (selectedRecommend.toString().equals("Selected Recommendation Response:\n")) {
            selectedRecommend.append("No recommendation option selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected stress level
        System.out.println(selectedRecommend); // Needs to be changed to save into a file / database
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            RemindersQuestion.loadRemindersQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            MotivateQuestion.loadMotivateQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}