package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PeriodTrackerController {

    @FXML
    private Label periodStatusLabel;

    @FXML
    private TextField lastPeriodField;

    @FXML
    private Button trackButton;

    @FXML
    private Button nextButton;

    @FXML
    protected void onTrackButtonClick() {
        String lastPeriodDate = lastPeriodField.getText().trim();

        if (!lastPeriodDate.isEmpty()) {
            periodStatusLabel.setText("Last period date: " + lastPeriodDate);


            nextButton.setVisible(true);
        } else {

            periodStatusLabel.setText("Please enter the last period date.");


            nextButton.setVisible(false);
        }
    }

    /*
    @FXML
    protected void onNextButtonClick() {
        try {
            Stage stage = (Stage) nextButton.getScene().getWindow();
            PeriodDetails.loadPeriodDetailsScene(stage); // Call the static method
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace for debugging
        }


    }

     */
}
