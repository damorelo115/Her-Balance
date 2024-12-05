package herbalance.herbalance;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();
    public static User theUser;

    @Override
    public void start(Stage stage) {

        try {
            fstore = contxtFirebase.firebase();
            fauth = FirebaseAuth.getInstance();
            theUser = new User();
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

