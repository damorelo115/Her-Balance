package herbalance.herbalance;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;

public class UserLoginController {

    @FXML
    private Label loginResult;

    @FXML
    private TextField useremail;


    @FXML
    private PasswordField userpassword;


    @FXML
    private Button signinButton;


    @FXML
    private Button signUpButton;


    @FXML
    protected void onSignUpButtonClick() throws IOException {

        try {
            Stage stage = (Stage) signUpButton.getScene().getWindow();

            UserRegistration.loadUserRegistrationScene(stage);
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @FXML
    protected void onSignInButtonClick() throws IOException, ExecutionException, InterruptedException {

        if (useremail.getText().isEmpty() || userpassword.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Please enter your email and password!");

        }

       else if (signInUser() == true) {

            showAlert(Alert.AlertType.CONFIRMATION, "Sign in successful!");

        }

    }

    public boolean signInUser() throws ExecutionException, InterruptedException {

        String enteredEmail = useremail.getText();
        String enteredPassword = userpassword.getText();
        String documentEmail, documentPassword;

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = Main.fstore.collection("Users").get();

        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;

        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {

                    documentEmail = String.valueOf(document.getData().get("Email"));
                    documentPassword = String.valueOf(document.getData().get("Password"));

                    if (documentEmail.equals(enteredEmail) && documentPassword.equals(enteredPassword)) {

                        Stage stage = (Stage) signinButton.getScene().getWindow();

                        stage.close();

                        Dashboard.loadDashboardScene();

                        showAlert(Alert.AlertType.CONFIRMATION, "Sign in successful!");

                    }

                }

            }

            else {
                showAlert(Alert.AlertType.INFORMATION, "User not logged in!");
            }


        }

        catch (InterruptedException | ExecutionException e) {

            throw new RuntimeException(e);
        }

        return false;
    }

    // showAlert method
    private void showAlert(Alert.AlertType alertType, String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(alert.getTitle());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}

