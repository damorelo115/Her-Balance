package herbalance.herbalance;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

        stressCheckBox.selectedProperty().addListener(checkboxListener);
        physicalActivityCheckBox.selectedProperty().addListener(checkboxListener);
        sleepCheckBox.selectedProperty().addListener(checkboxListener);
        reproductiveHealthCheckBox.selectedProperty().addListener(checkboxListener);
    }

    // Helper method to check if any checkbox is selected
    private boolean isAnyCheckboxSelected() {
        return stressCheckBox.isSelected() || physicalActivityCheckBox.isSelected() ||
                sleepCheckBox.isSelected() || reproductiveHealthCheckBox.isSelected();
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            ActivityLevelQuestion.loadActivityLevelQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            WellnessGoalsQuestion.loadWellnessGoalsQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

