package herbalance.herbalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard2 extends Application  {


    public static void loadDashboardScene() {
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard2.class.getResource("Dashboard2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 845, 595);
        stage.setTitle("Create account");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
