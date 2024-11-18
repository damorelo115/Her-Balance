package herbalance.herbalance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PeriodTracker  {

    public static void loadPeriodTrackerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeriodTracker.class.getResource("PeriodTracker.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Period Tracker");
        stage.setScene(scene);
        stage.show();
    }


}

