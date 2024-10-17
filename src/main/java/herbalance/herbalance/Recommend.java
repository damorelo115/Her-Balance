package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Recommend {

    // Static method to load the Recommend scene
    public static void loadRecommendScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.Recommend.class.getResource("Recommend.fxml"));

        // Load the FXML file and set up the new scene
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
