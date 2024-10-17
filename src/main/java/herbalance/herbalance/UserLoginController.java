package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class UserLoginController {


    @FXML
    private Label loginResult;


    @FXML
    private PasswordField passwordTextField;


    @FXML
    private Button signinButton;


    @FXML
    private Button signUpButton;


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


    @FXML
    protected void onSignUpButtonClick() throws IOException {

        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(UserLogin.class.getResource("UserRegistration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Create an Account");
        stage.setScene(scene);
        stage.show();

    }


}
