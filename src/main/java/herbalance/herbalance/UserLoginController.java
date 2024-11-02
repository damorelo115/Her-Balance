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
    void signInButtonClicked(ActionEvent event) throws FirebaseAuthException {
        signInUser();
    }

    public boolean signInUser() {

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(usernameTextField.getText());

            System.out.println("Successfully signed in user with User email: " + userRecord.getEmail());
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Incorrect email and password entered");
            return false;
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

