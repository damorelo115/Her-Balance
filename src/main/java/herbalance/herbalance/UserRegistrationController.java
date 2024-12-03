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

import static herbalance.herbalance.Main.fauth;
import static herbalance.herbalance.Main.theUser;

public class UserRegistrationController {

    @FXML
    private TextField firstnameField;

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


    // Calls the onRegisterButtonClick method to register a user
    @FXML
    protected void onRegisterButtonClick() throws IOException {

        if (userEmail.getText().isEmpty() || userPassword.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Please enter your email and password!");

            userEmail.clear();
            userPassword.clear();

        }

        else {

            if (registerUser()) {

                if (addUser()) {

                    theUser.setUserEmail(userEmail.getText());
                    theUser.setPassword(userPassword.getText());

                    userEmail.clear();
                    userPassword.clear();

                    Stage stage = (Stage) registerButton.getScene().getWindow();

                    //NameEntryQuestion.loadNameEntryQuestionScene(stage);

                    BirthDateQuestion.loadBirthDateQuestionScene(stage);

                    showAlert(Alert.AlertType.CONFIRMATION, "Registration successful!");

                }

            }

            else {

                checkUserEmail(userEmail.getText());

            }

        }
    }

    // This method is called when registering a new user. This method will add the username and password to a collection in Firestore
    public boolean addUser() {

        DocumentReference docRef = Main.fstore.collection("Users").document(UUID.randomUUID().toString());

        Map<String, Object> data = new HashMap<>();
        data.put("Email", userEmail.getText());
        data.put("Password", userPassword.getText());
        data.put("FirstName", firstnameField.getText());

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

            fauth.createUser(request);

            return true;

        }

        catch (FirebaseAuthException ex) {

            return false;
        }

    }

    // Checks if the user already exists when trying to register an account
    public void checkUserEmail(String email) {
        try {

            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);

            if (userRecord.getEmail().equals(userEmail.getText())) {

                showAlert(Alert.AlertType.INFORMATION, "User already exists!");

            }

            else {

                showAlert(Alert.AlertType.ERROR, "Invalid email address!");

            }

        }

        catch (Exception e) {

            throw new RuntimeException(e);
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

    // showAlert method to display alerts
    private void showAlert (Alert.AlertType alertType, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(alert.getTitle());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}