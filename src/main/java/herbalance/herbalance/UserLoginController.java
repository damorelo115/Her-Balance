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

            useremail.clear();

            userpassword.clear();

        }

        else {

            if (signInUser() == true) {

                showAlert(Alert.AlertType.CONFIRMATION, "Sign in successful!");

                Stage stage = (Stage) signinButton.getScene().getWindow();

                Dashboard.loadDashboardScene();

            }

        }

    }

    public boolean signInUser() throws ExecutionException, InterruptedException {

        String email = useremail.getText();
        String password = userpassword.getText();

        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = Main.fstore.collection("Users").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        try {
            documents = future.get().getDocuments();

            if (!documents.isEmpty()) {

                for (QueryDocumentSnapshot document : documents) {

                    email = String.valueOf(document.getData().get("email"));

                    password = String.valueOf(document.getData().get("password"));

                    if (useremail.getText().equals("janedoe@gmail.com") && userpassword.getText().equals("password") ) {

                        Stage stage = (Stage) signinButton.getScene().getWindow();

                        stage.close();

                        Dashboard.loadDashboardScene();
                    }

                   else {

                        showAlert(Alert.AlertType.ERROR,"User does not exist! Please try again!");

                        useremail.clear();

                        userpassword.clear();
                    }

                }

            }


        } catch (InterruptedException | ExecutionException e) {

            return false;
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


