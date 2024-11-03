package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MotivateQuestion {

    // Static method to load the Motivate scene
    public static void loadMotivateQuestionScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.MotivateQuestion.class.getResource("MotivateQuestion.fxml"));

        // Load the FXML file and set up the new scene
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
