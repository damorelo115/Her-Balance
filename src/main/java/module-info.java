module herbalance.herbalance {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens herbalance.herbalance to javafx.fxml;
    exports herbalance.herbalance;
}