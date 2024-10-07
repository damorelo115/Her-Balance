package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HerBalanceController {
    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Button submitButton;

    @FXML
    protected void onButtonClick() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            nameLabel.setText("Hello, " + name + "!");
        } else {
            nameLabel.setText("Please enter your name.");
        }
    }
}