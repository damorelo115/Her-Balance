package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class RecommendQuestionController {

    // Radio button for yes selection
    @FXML
    private RadioButton yesRadioButton;

    // Radio button for no selection
    @FXML
    private RadioButton noRadioButton;

    // Back Button
    @FXML
    private Button backButton;

    // Next Button
    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Create a ToggleGroup for the radio buttons
        ToggleGroup recommendGroup = new ToggleGroup();
        yesRadioButton.setToggleGroup(recommendGroup);
        noRadioButton.setToggleGroup(recommendGroup);

        // Add a listener to the ToggleGroup to enable the Next button when a selection is made
        recommendGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            nextButton.setDisable(newValue == null); // Enable Next button only when a selection is made
        });

        // Initially disable the Next button
        nextButton.setDisable(true);
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
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
