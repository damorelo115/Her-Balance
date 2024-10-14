package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SleepDuration {

    // Static method to load the Wellness Goals scene
    public static void loadSleepDurationScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.SleepDuration.class.getResource("SleepDuration.fxml"));

        // Load the FXML file and set up the new scene
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
