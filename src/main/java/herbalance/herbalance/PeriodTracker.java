package herbalance.herbalance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class PeriodTracker {

    public static void loadPeriodTrackerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeriodTracker.class.getResource("PeriodTracker.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 479);
        stage.setTitle("Period Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void  main (String [] args) {
        launch();
    }
}