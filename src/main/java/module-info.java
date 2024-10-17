module herbalance.herbalance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens herbalance.herbalance to javafx.fxml;
    exports herbalance.herbalance;
}