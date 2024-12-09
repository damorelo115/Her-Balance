package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PeriodTracker {
    private LocalDate lastPeriodStartDate; // Last known period start date
    private int cycleLength; // Average cycle length in days
    private int periodLength; // Period length in days

    // Constructor
    public PeriodTracker(LocalDate lastPeriodStartDate, int cycleLength) {
        if (cycleLength <= 0) {
            throw new IllegalArgumentException("Cycle length must be a positive number.");
        }
        this.lastPeriodStartDate = lastPeriodStartDate;
        this.cycleLength = cycleLength;
        this.periodLength = 5; // Default period length to 5 days
    }

    // Static method to load the Period Tracker UI
    public static void loadPeriodTrackerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeriodTracker.class.getResource("PeriodTracker.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Period Tracker");
        stage.setScene(scene);
        stage.show();
    }

    // Static factory method for creating a PeriodTracker instance
    public static PeriodTracker createPeriodTracker(LocalDate lastPeriodStartDate, int cycleLength) {
        return new PeriodTracker(lastPeriodStartDate, cycleLength);
    }

    // Getters and setters
    public LocalDate getLastPeriodStartDate() {
        return lastPeriodStartDate;
    }

    public void setLastPeriodStartDate(LocalDate lastPeriodStartDate) {
        this.lastPeriodStartDate = lastPeriodStartDate;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        if (cycleLength <= 0) {
            throw new IllegalArgumentException("Cycle length must be positive.");
        }
        this.cycleLength = cycleLength;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        if (periodLength <= 0) {
            throw new IllegalArgumentException("Period length must be positive.");
        }
        this.periodLength = periodLength;
    }

    // Predict the start date of the next cycle
    public LocalDate predictNextCycleStart() {
        return lastPeriodStartDate.plusDays(cycleLength);
    }

    // Predict the end date of the next cycle
    public LocalDate predictNextCycleEnd() {
        return predictNextCycleStart().plusDays(periodLength - 1);
    }

    // Predict the ovulation date
    public LocalDate predictOvulation() {
        // Ovulation occurs approximately 14 days before the next cycle starts
        return predictNextCycleStart().minusDays(14);
    }

    // Generate a summary of period details
    public String getPeriodDetails() {
        return String.format(
                "Last Period Start: %s\nCycle Length: %d days\nPeriod Length: %d days\nNext Period Start: %s\nNext Period End: %s\nOvulation Date: %s",
                lastPeriodStartDate, cycleLength, periodLength, predictNextCycleStart(), predictNextCycleEnd(), predictOvulation()
        );
    }
}

