package herbalance.herbalance;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
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
    void registerButtonClicked(ActionEvent event) {
        onRegisterButtonClick ();
    }

    @FXML
    public boolean onRegisterButtonClick() {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(userName.getText())
                .setEmailVerified(false)
                .setPassword(userPassword.getText());
        /*
                .setDisplayName(name);
                .setDisabled(false);
                .setPhoneNumber("");
         */

        UserRecord userRecord;
        try {
            userRecord = UserRegistration.fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error creating a new user in the firebase");
            return false;
        }
    }

}












