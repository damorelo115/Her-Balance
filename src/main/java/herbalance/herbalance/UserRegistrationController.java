package herbalance.herbalance;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRegistrationController {

    @FXML
    private Label passwordLabel;

    @FXML
    private Button registerButton;

    @FXML
    private Button signinButton;

    @FXML
    private TextField userEmail;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label usernameLabel;


    @FXML
    protected void onRegisterButtonClick() throws IOException {

        if (userEmail.getText().isEmpty() || userPassword.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Please enter your email and password!");

            userEmail.clear();
            userPassword.clear();

        }

        else {

            if (registerUser() == true ) {

                if (addUser() == true ) {

                    userEmail.clear();

                    userPassword.clear();

                    Stage stage = (Stage) registerButton.getScene().getWindow();

                    NameEntryQuestion.loadNameEntryQuestionScene(stage);

                    showAlert(Alert.AlertType.CONFIRMATION, "Registration successful!");

                }

                else {

                    showAlert(Alert.AlertType.ERROR, "Registration failed!");

                    userEmail.clear();

                    userPassword.clear();
                }

            }

            else {

                showAlert(Alert.AlertType.ERROR, "Registration failed, issue with registering the user! Try again!");

                userEmail.clear();

                userPassword.clear();
            }
            

        }
    }

    // This method is called when registering a new user. This method will add the username and password to a collection in Firestore
    public boolean addUser() {

        DocumentReference docRef = Main.fstore.collection("Users").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("Email", userEmail.getText());
        data.put("Password", userPassword.getText());

        try {
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
        }

         catch (Exception ex) {

            return false;
        }

        return true;

    }

    // This method is called when adding a new authenticated user to Firebase Authentication
    public boolean registerUser() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(userEmail.getText())
                .setEmailVerified(false)
                .setPassword(userPassword.getText());

        UserRecord userRecord;

        try {

            Main.fauth.createUser(request);

            return true;

        }

        catch (FirebaseAuthException ex) {

            //Logger.getLogger(UserRegistrationController.class.getName()).log(Level.SEVERE, null, ex);

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

    // showAlert method
    private void showAlert (Alert.AlertType alertType, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(alert.getTitle());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }



}






