package herbalance.herbalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;

public class Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) throws IIOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard2.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Dashboard App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main (String[] args){
        launch(args);
    }

}

