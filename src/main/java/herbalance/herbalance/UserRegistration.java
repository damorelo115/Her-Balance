package herbalance.herbalance;

import com.google.firebase.auth.AbstractFirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegistration {

    private  String username;
    private  String password;

    public static void loadUserRegistrationScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserLogin.class.getResource("UserRegistration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 690 , 471);
        stage.setTitle("User Registration");
        stage.setScene(scene);
        stage.show();
    }


    public UserRegistration(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

}





