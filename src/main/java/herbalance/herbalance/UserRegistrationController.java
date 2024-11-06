package herbalance.herbalance;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
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
    protected void onRegisterButtonClick() throws IOException {

        registerUser();

        //Will comment out when working - Natasia Stage stage = (Stage) registerButton.getScene().getWindow();

        // Will comment out when working - Natasia NameEntryQuestion.loadNameEntryQuestionScene(stage);

    }
    public boolean registerUser() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(userName.getText())
                .setEmailVerified(false)
                .setPassword(userPassword.getText());
                //.setPhoneNumber("+11234567890")
                //.setDisplayName("John Doe")
                //.setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = Main.fauth.createUser(request);
            // to do: make sure the user sees  success message on the window
            // System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
             //       + " check Firebase > Authentication > Users tab");
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error creating a new user in the firebase");
            // to do: make sure the user sees  error message on the screen
            return false;
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

    protected void onSigUpButtonClick() {

        try {
            Stage stage = (Stage) signinButton.getScene().getWindow();

            UserLogin.loadUserLoginScene(stage);
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    }






