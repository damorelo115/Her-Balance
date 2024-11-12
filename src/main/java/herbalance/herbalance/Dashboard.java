package herbalance.herbalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard extends Application {

    public static void loadDashboardScene() {
        try {
            FXMLLoader loader = new FXMLLoader(Dashboard.class.getResource("Dashboard.fxml"));
            Parent root = loader.load();

            Scene dashboardScene = new Scene(root);
            Stage dashboardStage = new Stage();
            dashboardStage.setScene(dashboardScene);
            dashboardStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("FXML file not found or path is incorrect. Please verify the file path.");
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 845, 595);
        stage.setTitle("Create account");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

