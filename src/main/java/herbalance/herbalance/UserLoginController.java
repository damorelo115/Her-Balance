package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import com.google.firebase.auth.*;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;

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
    protected void onSignUpButtonClick() throws IOException {

        try {
            Stage stage = (Stage) signUpButton.getScene().getWindow();

            UserRegistration.loadUserRegistrationScene(stage);
        }

        catch (IOException e) {

            e.printStackTrace();
        }

    }

    @FXML
    protected void onSignInButtonClick() throws IOException {

        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Dashboard.loadDashboardScene(stage);
    }
}