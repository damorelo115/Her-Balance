package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class BirthDate {

    public static void loadBirthdateScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BirthDate.class.getResource("BirthDate.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
