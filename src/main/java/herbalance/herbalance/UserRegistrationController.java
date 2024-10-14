package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


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
    private Label signinButton;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label usernameLabel;

    @FXML
    private void registration() {


        if (userName.getText().isEmpty()) {

            usernameLabel.setText("Please enter your username");
        }

        else {

            usernameLabel.setText("Username confirmed");
        }

    }

        @FXML
        private void createpassword() {

            if (userPassword.getText().isEmpty()) {

                passwordLabel.setText("Please enter your password");

            }
            else {

                usernameLabel.setText("Password confirmed");
            }
        }


        @FXML
        private void passwordconfirm() {

            if (confirmPassword.getText().isEmpty()) {

                confirmLabel.setText("Please confirm your password");

                confirmLabel.setTextFill(Color.rgb(210, 39, 30));

            }

            else {

                confirmLabel.setText("Password confirmed");
            }
        }


        @FXML
        private void onButtonClick() {

            String username = userName.getText();

            String password = userPassword.getText();

            String confirmation = confirmPassword.getText();


            if (!username.isEmpty() && !password.isEmpty() && !confirmation.isEmpty()) {

                registrationResult.setText("Registration successful! " + " Welcome " + username);
            }

            else {

                registrationResult.setText("Registration unsuccessful! Please enter your information!");

            }
        }

    }

