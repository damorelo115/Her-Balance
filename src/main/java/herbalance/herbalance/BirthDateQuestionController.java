package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BirthDateQuestionController {

    // Date picker for selecting the birthdate
    @FXML
    private DatePicker birthDatePicker;

    // Label to display the selected birthdate
    @FXML
    private Label birthDateLabel;

    @FXML
    private Label validationLabel;

    // Button for Next Button
    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {
        // Set up a listener to monitor changes to the birthDatePicker
        birthDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Display the selected birthdate in the label
                birthDateLabel.setText("Your birthdate: " + newValue.toString());

                // Hide validation message
                validationLabel.setVisible(false);

                // Enable the Next button
                nextButton.setDisable(false);
            } else {
                // Ask the user to select a birthdate
                validationLabel.setText("Please select a birthdate.");
                validationLabel.setVisible(true);

                // Disable the Next button
                nextButton.setDisable(true);
            }
        });

        // Initially disable the Next button
        nextButton.setDisable(true);
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            WellnessGoalsQuestion.loadWellnessGoalsQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
