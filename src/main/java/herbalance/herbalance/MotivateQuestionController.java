package herbalance.herbalance;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MotivateQuestionController {

    @FXML
    private CheckBox personalCheckBox;

    @FXML
    private CheckBox mentalCheckBox;

    @FXML
    private CheckBox physicalCheckBox;

    @FXML
    private CheckBox energyCheckBox;

    @FXML
    private CheckBox stressCheckBox;

    @FXML
    private CheckBox disciplineCheckBox;

    @FXML
    private CheckBox longHealthCheckBox;

    @FXML
    private CheckBox confidenceCheckBox;

    @FXML
    private CheckBox noneCheckBox;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Create a list of all checkboxes
        List<CheckBox> checkBoxes = List.of(
                personalCheckBox, mentalCheckBox, physicalCheckBox, energyCheckBox,
                stressCheckBox, disciplineCheckBox, longHealthCheckBox, confidenceCheckBox, noneCheckBox
        );

        // Add a common listener to all checkboxes
        checkBoxes.forEach(checkbox -> checkbox.selectedProperty().addListener(createCheckboxListener(checkBoxes)));

        // Initially disable the Next button
        nextButton.setDisable(true);
    }

    // Method to create a checkbox listener
    private ChangeListener<Boolean> createCheckboxListener(List<CheckBox> checkBoxes) {
        return (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            // Enable the Next button if any checkbox is selected
            nextButton.setDisable(checkBoxes.stream().noneMatch(CheckBox::isSelected));
        };
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
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
            ActivityLevelQuestion.loadActivityLevelQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


