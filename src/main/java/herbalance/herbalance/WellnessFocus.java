package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WellnessFocus {

        // Static method to load the Wellness Goals scene
        public static void loadWellnessFocusScene(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.WellnessFocus.class.getResource("WellnessFocus.fxml"));

            // Load the FXML file and set up the new scene
            Scene scene = new Scene(fxmlLoader.load(), 690, 471);
            stage.setTitle("Welcome to HerBalance");
            stage.setScene(scene);
            stage.show();
        }
    }

