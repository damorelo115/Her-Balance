package herbalance.herbalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;

public class Dashboard {

    public static void loadDashboardScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NameEntryQuestion.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Welcome to HerBalance");
        stage.setScene(scene);
        stage.show();
    }
    /*


    @Override
    public void start(Stage primaryStage) throws IIOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
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

     */

}

