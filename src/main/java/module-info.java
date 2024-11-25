module herbalance.herbalance {

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
    requires java.net.http;
    requires org.json;
    requires java.logging;
    requires org.testng;


    opens herbalance.herbalance to javafx.fxml;
    exports herbalance.herbalance;
}