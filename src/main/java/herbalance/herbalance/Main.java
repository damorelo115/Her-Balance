package herbalance.herbalance;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) {
        try {

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

