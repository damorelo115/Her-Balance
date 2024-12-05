module herbalance.herbalance {
    requires java.logging;

    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.api.apicommon;



    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.fxml;

    opens herbalance.herbalance to javafx.fxml;
    exports herbalance.herbalance;
}