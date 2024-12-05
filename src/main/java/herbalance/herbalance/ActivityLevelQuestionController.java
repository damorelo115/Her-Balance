package herbalance.herbalance;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ActivityLevelQuestionController {

    @FXML
    // Checkbox for sedentary option
    private CheckBox sedentaryCheckBox;

    // Checkbox for moderately active option
    @FXML
    private CheckBox moderatelyActiveCheckBox;

    // Checkbox for highly active option
    @FXML
    private CheckBox highlyActiveCheckBox;

    // Back Button
    @FXML
    private Button backButton;

    // Next Button
    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Initially disable the Next button
        nextButton.setDisable(true);

        // Add listeners to all checkboxes
        ChangeListener<Boolean> checkboxListener = (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            // Check if any checkbox is selected
            if (isAnyCheckboxSelected()) {
                nextButton.setDisable(false);
            } else {
                nextButton.setDisable(true);
            }
        };

        sedentaryCheckBox.selectedProperty().addListener(checkboxListener);
        moderatelyActiveCheckBox.selectedProperty().addListener(checkboxListener);
        highlyActiveCheckBox.selectedProperty().addListener(checkboxListener);
    }

    // Helper method to check if any checkbox is selected
    private boolean isAnyCheckboxSelected() {
        return sedentaryCheckBox.isSelected() || moderatelyActiveCheckBox.isSelected() || highlyActiveCheckBox.isSelected();
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
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
            WellnessFocusQuestion.loadWellnessFocusQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

