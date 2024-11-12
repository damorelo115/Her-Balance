package herbalance.herbalance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLogin {

    public static void loadUserLoginScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserLogin.class.getResource("UserLogin.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 690 , 471);
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();
    }

    private String username;
    private String password;

    public UserLogin(String username, String password) {
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




