package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DietController {

    @FXML
    // Checkbox for vegetarian diet
    private CheckBox vegetarianCheckBox;

    // Checkbox for vegan diet
    @FXML
    private CheckBox veganCheckBox;

    // Checkbox for gluten-free diet
    @FXML
    private CheckBox glutenFreeCheckBox;

    // Checkbox for other diet
    @FXML
    private CheckBox otherCheckBox;

    // Checkbox for no diet
    @FXML
    private CheckBox noneCheckBox;

    // Submit Button
    @FXML
    private Button submitButton;

    // Next Button
    @FXML
    private Button nextButton;

    // Method triggered when Submit button is clicked
    @FXML
    protected void onSubmitButtonClick() {
        // Declaring and initializing a StringBuilder to store selected goals
        StringBuilder selectedDiet = new StringBuilder();

        if (vegetarianCheckBox.isSelected()) {
            selectedDiet.append("- Vegetarian\n");
        }
        if (veganCheckBox.isSelected()) {
            selectedDiet.append("- Vegan\n");
        }
        if (glutenFreeCheckBox.isSelected()) {
            selectedDiet.append("- Gluten-free\n");
        }
        if (otherCheckBox.isSelected()) {
            selectedDiet.append("- Other\n");
        }
        if (noneCheckBox.isSelected()) {
            selectedDiet.append("- No diet\n");
        }

        // Checking if no goals are selected
        if (selectedDiet.toString().equals("Selected Dietary Preference:\n")) {
            selectedDiet.append("No diet selected.");
            // Next button will be hidden if no goals are selected
            nextButton.setVisible(false);

            // Disabling the submit button after submission
            submitButton.setDisable(true);
        } else {
            // Next button will be visible if goals are selected
            nextButton.setVisible(true);
        }
        // Printing selected diet
        System.out.println(selectedDiet); // Needs to be changed to save into a file / database
    }
    // Method called when the Next button is clicked
    @FXML
      protected void onNextButtonClick () {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            Stress.loadStressScene(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
