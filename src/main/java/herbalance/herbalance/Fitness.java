package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Fitness {

    public static void loadFitnessTrackerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Fitness.class.getResource("FitnessTracker.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fitness Tracker");
        stage.setScene(scene);
        stage.show();
    }
}
