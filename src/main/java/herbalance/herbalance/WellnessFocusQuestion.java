package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WellnessFocusQuestion {

        // Static method to load the Wellness Goals scene
        public static void loadWellnessFocusQuestionScene(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.WellnessFocusQuestion.class.getResource("WellnessFocusQuestion.fxml"));

            // Load the FXML file and set up the new scene
            Scene scene = new Scene(fxmlLoader.load(), 690, 471);
            stage.setTitle("Welcome to HerBalance");
            stage.setScene(scene);
            stage.show();
        }
    }

