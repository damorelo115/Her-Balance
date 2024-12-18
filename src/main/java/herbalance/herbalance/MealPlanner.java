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

    private String breakfast;
    private String lunch;
    private String dinner;
    private String day;

    public MealPlanner(String breakfast, String lunch, String dinner, String day) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String days) {
        this.day = days;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

}








