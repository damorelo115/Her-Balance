package herbalance.herbalance;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationControllerTest {

    @BeforeAll
    static void setUp() {

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

    /* Tests if an email exists in Firebase Authentication
     If the user is already registered
     */
    @Test
    @DisplayName("User already exists")
    void emailAlreadyRegistered() {

        // Test with a valid email registered in Firebase
        String email = "janedoe@gmail.com";
        try {
            UserRecord user = FirebaseAuth.getInstance().getUserByEmail(email);
            assertNotNull(user, "User with this email exists.");
        }
        catch (Exception e) {

            fail("Error occurred while checking for registered email: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("User does not exist")
    void emailDoesNotExist() throws FirebaseAuthException {
        // Test with a valid email registered in Firebase
        String email = "sarahjones@gmail.com";

        try {

            UserRecord user = FirebaseAuth.getInstance().getUserByEmail(email);

            //assertNull(user, "User with this email does not exist.");

            fail("Error occurred while checking for registered email");

        }
        catch (FirebaseAuthException e) {

            assertTrue(true, "User with this email does not exist.");

        }
    }

}