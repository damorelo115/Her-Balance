package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WellnessGoalsQuestion {

    // Static method to load the Wellness Goals scene
    public static void loadWellnessGoalsQuestionScene(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(WellnessGoalsQuestion.class.getResource("WellnessGoalsQuestion.fxml"));

        // Load the FXML file and set up the new scene
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
