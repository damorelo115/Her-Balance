package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PeriodTracker {

    public static void loadPeriodTrackerScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PeriodTracker.class.getResource("PeriodTracker.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Period Tracker");
        stage.setScene(scene);
        stage.show();
    }

    private LocalDate PreviousPeriodStartDate;
    private int cycleLength;
    private int periodLength;


    public PeriodTracker(LocalDate PreviousPeriodStartDate, int cycleLength, int periodLength) {
        this.PreviousPeriodStartDate = PreviousPeriodStartDate;
        this.cycleLength = cycleLength;
        this.periodLength = periodLength;
    }


    public LocalDate getPreviousPeriodStartDate() {
        return PreviousPeriodStartDate;
    }

    public void setPreviousPeriodStartDate(LocalDate PreviousPeriodStartDate) {
        this.PreviousPeriodStartDate = PreviousPeriodStartDate;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        this.cycleLength = cycleLength;
    }

    public int getPeriodLength() {
        return periodLength;
    }

    public void setPeriodLength(int periodLength) {
        this.periodLength = periodLength;
    }


    public LocalDate predictNextCycleStart() {
        return PreviousPeriodStartDate.plusDays(cycleLength);
    }


    public LocalDate predictNextCycleEnd() {
        return predictNextCycleStart().plusDays(periodLength);
    }


    public void updateTracker(LocalDate PreviousPeriodStartDate, int cycleLength, int periodLength) {
        setPreviousPeriodStartDate(PreviousPeriodStartDate);
        setCycleLength(cycleLength);
        setPeriodLength(periodLength);
    }


    public String getPeriodDetails() {
        return "Last Period Start: " + PreviousPeriodStartDate +
                "\nCycle Length: " + cycleLength + " days" +
                "\nPeriod Length: " + periodLength + " days" +
                "\nNext Period Start: " + predictNextCycleStart() +
                "\nNext Period End: " + predictNextCycleEnd();
    }
}