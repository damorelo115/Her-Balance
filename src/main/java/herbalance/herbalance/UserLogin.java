package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLogin {

    public static void loadUserLoginScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserLogin.class.getResource("UserLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 735, 479);
        stage.setTitle("HerBalance Login");
        stage.setScene(scene);
        stage.show();
    }

}


