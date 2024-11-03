package herbalance.herbalance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NameEntryQuestion {
    public static void loadNameEntryQuestionScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NameEntryQuestion.class.getResource("NameEntryQuestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
