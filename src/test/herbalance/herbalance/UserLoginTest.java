package herbalance.herbalance;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class UserLoginTest {

    private UserLoginController controller;
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private Button signInButton;

    @BeforeEach
    public void setUp() {

        emailTextField = new TextField();
        passwordTextField = new PasswordField();
        signInButton = new Button("Sign In");

        signInButton.setOnAction(event -> {
            try {
                controller.signInUser();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (RuntimeException | IOException e) {

            throw new RuntimeException(e);

        }

    }



    @Test
    @DisplayName("Successful User Login")
    void successfulLogin() throws IOException {

        try {

            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(emailTextField.getText());

            String email = "janedoe@gmail.com";
            String password = "password";

            emailTextField.setText(email);
            passwordTextField.setText(password);

            signInButton.setDisable(true);
        }

        catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

}