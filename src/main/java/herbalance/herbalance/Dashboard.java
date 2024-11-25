package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {

    public static void loadDashboardScene() {
        try {
            FXMLLoader loader = new FXMLLoader(Dashboard.class.getResource("Dashboard.fxml"));
            Parent root = loader.load();

            Scene dashboardScene = new Scene(root);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Dashboard");
            dashboardStage.setScene(dashboardScene);
            dashboardStage.show();
            dashboardStage.setResizable(false);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("FXML file not found or path is incorrect. Please verify the file path.");
            e.printStackTrace();
        }
    }

}



