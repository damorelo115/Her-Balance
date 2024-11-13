package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class NameEntryQuestionController {
    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    // Method called when the Submit button is clicked
    @FXML
    protected void onButtonClick() {
        String name = nameField.getText();

        // Checking if the name field is not empty
        if (!name.isEmpty()) {

            // Updating the label with "Hello, <name>!"
            nameLabel.setText("Hello, " + name + "!");
            Main.theUser.setFirstName(name);

            // Making the Next button visible
            nextButton.setVisible(true);


        } else {
            // If the name is empty, ask the user to enter their name
            nameLabel.setText("Please enter your name.");

            // Next button will be hidden if no name is entered
            nextButton.setVisible(false);
        }
    }

    // Method called when the Next button is clicked
    @FXML
    protected void onNextButtonClick() {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            BirthDateQuestion.loadBirthDateQuestionScene(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method called when the Back button is clicked
    @FXML
    protected void onBackButtonClick() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            UserRegistration.loadUserRegistrationScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
