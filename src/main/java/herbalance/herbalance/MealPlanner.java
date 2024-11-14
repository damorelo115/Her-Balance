package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MealPlanner {

    public static void loadMealPlannerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MealPlanner.class.getResource("MealPlanner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Meal Planner");
        stage.setScene(scene);
        stage.show();
    }
}
