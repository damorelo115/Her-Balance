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

    // Button for Back Button
    @FXML
    private Button backButton;

    // Button for Submit Button
    @FXML
    private Button submitButton;

    // Button for Next Button
    @FXML
    private Button nextButton;

    // Method triggered when the Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        if (birthDatePicker.getValue() != null) {
            // Displaying the selected birthdate in the label
            birthDateLabel.setText("Your birthdate: " + birthDatePicker.getValue().toString());

            // Hiding validation message
            validationLabel.setVisible(false);

            // Making the Next button visible
            nextButton.setVisible(true);
        } else {
            // Asking the user to select a birthdate if they did not do so
            validationLabel.setText("Please select a birthdate.");
            validationLabel.setVisible(true);

            // Next button will be hidden if no name is entered
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        }
    }
        // Method called when the Next button is clicked
        @FXML
        protected void onNextButtonClick () {
            try {
                Stage stage = (Stage) nextButton.getScene().getWindow();
                WellnessGoalsQuestion.loadWellnessGoalsQuestionScene(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            NameEntryQuestion.loadNameEntryQuestionScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
