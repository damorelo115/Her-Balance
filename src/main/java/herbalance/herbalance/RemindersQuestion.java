package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RemindersQuestion {

    // Static method to load the Reminders scene
    public static void loadRemindersQuestionScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(herbalance.herbalance.RemindersQuestion.class.getResource("RemindersQuestion.fxml"));

        // Load the FXML file and set up the new scene
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
