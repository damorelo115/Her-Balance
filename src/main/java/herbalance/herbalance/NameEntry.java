package herbalance.herbalance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NameEntry{
    public static void loadNameEntryScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NameEntry.class.getResource("NameEntry.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
}
