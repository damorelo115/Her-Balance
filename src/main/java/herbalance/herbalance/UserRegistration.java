package herbalance.herbalance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegistration  extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserLogin.class.getResource("UserRegistration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690, 471);
        stage.setTitle("Create an Account");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

