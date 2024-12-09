package herbalance.herbalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Fitness {

    // Fields for Fitness attributes
    private String cardio;
    private String strength;
    private String flexibility;
    private String workoutDay;

    // Constructor for Fitness
    public Fitness(String cardio, String strength, String flexibility, String workoutDay) {
        this.cardio = cardio;
        this.strength = strength;
        this.flexibility = flexibility;
        this.workoutDay = workoutDay;
    }

    // Getter and Setter methods
    public String getCardio() {
        return cardio;
    }

    public void setCardio(String cardio) {
        this.cardio = cardio;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(String flexibility) {
        this.flexibility = flexibility;
    }

    public String getWorkoutDay() {
        return workoutDay;
    }

    public void setWorkoutDay(String workoutDay) {
        this.workoutDay = workoutDay;
    }

    // Method to load the Fitness Tracker Scene
    public static void loadFitnessTrackerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Fitness.class.getResource("FitnessTracker.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fitness Tracker");
        stage.setScene(scene);
        stage.show();
    }
}

