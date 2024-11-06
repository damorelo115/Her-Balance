package herbalance.herbalance;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;

import com.google.firebase.auth.*;
import com.google.cloud.firestore.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    // Authenticate Firestore
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage stage) {
        try {

            fstore = contxtFirebase.firebase();
            fauth = FirebaseAuth.getInstance();

            UserLogin.loadUserLoginScene(stage);
        }

        catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load the NameEntry scene", e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

