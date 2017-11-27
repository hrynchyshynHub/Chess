package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by ivan.hrynchyshyn on 22.11.2017.
 */
public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    public void signIn(ActionEvent event) throws IOException{
        Stage stage =  (Stage)username.getScene().getWindow();
        String user = username.getText();
        String pass = password.getText();
        // go to server and find player;

        Parent root = FXMLLoader.load(getClass().getResource("view/Menu.fxml"));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();

    }

    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage)username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();
    }
}
