package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ActivityLevel {

    // Static method to load the Wellness Goals scene
    public static void loadActivityLevelScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.WellnessFocus.class.getResource("ActivityLevel.fxml"));

        // Load the FXML file and set up the new scene
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
