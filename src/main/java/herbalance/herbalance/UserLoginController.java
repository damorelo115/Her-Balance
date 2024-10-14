package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class UserLoginController {


    @FXML
    private Label loginResult;


    @FXML
    private PasswordField passwordTextField;


    @FXML
    private Button signinButton;


    @FXML
    private TextField usernameTextField;


    @FXML
    private void Login(ActionEvent event) {

        String username = usernameTextField.getText();

        String password = passwordTextField.getText();

                if (username.equals("username") && password.equals("password")) {

                    loginResult.setText("Login Successful!");
                }

                else {

                    loginResult.setText("Login Failed! Please try again!");
                }

    }

}
