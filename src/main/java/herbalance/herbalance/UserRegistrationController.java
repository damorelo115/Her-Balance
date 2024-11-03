package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;


public class UserRegistrationController {

    @FXML
    private Label confirmLabel;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Label registrationResult;

    @FXML
    private Button signinButton;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label usernameLabel;

    @FXML
    private void registrationValidation() {


        if (userName.getText().isEmpty()) {

            usernameLabel.setText("Please enter your username");
        }
        else {

            usernameLabel.setText("Username confirmed");
        }

        if (userPassword.getText().isEmpty()) {

            passwordLabel.setText("Please enter your password");

        } else {

            passwordLabel.setText("Password confirmed");

        }


        if (confirmPassword.getText().isEmpty()) {

            confirmLabel.setText("Please confirm your password");

            confirmLabel.setTextFill(Color.rgb(210, 39, 30));

        }

        else {

            confirmLabel.setText("Password confirmed");
        }

    }

    @FXML
    protected void onRegisterButtonClick() {

        try {

            Stage stage = (Stage) registerButton.getScene().getWindow();
            NameEntry.loadNameEntryScene(stage);
        }

        catch (IOException e) {

            e.printStackTrace();
        }

    }

    @FXML
    protected void onSignInButtonClick() {

        try {
            Stage stage = (Stage) signinButton.getScene().getWindow();

            UserLogin.loadUserLoginScene(stage);
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

}






