package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BirthDateQuestion {

    public static void loadBirthDateQuestionScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BirthDateQuestion.class.getResource("BirthDateQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690 , 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
