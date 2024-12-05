package herbalance.herbalance;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    // Back button
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

        fitnessCheckBox.selectedProperty().addListener(checkboxListener);
        mentalHealthCheckBox.selectedProperty().addListener(checkboxListener);
        nutritionCheckBox.selectedProperty().addListener(checkboxListener);
        hormonalBalanceCheckBox.selectedProperty().addListener(checkboxListener);
    }

    // Helper method to check if any checkbox is selected
    private boolean isAnyCheckboxSelected() {
        return fitnessCheckBox.isSelected() || mentalHealthCheckBox.isSelected() ||
                nutritionCheckBox.isSelected() || hormonalBalanceCheckBox.isSelected();
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            WellnessFocusQuestion.loadWellnessFocusQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            BirthDateQuestion.loadBirthDateQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


