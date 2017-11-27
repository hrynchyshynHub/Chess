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
public class RegisterController {
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField secondName;
    @FXML
    private TextField password;


    public void signUp(ActionEvent event){
//        Player player = new Player(username.getText(), firstName.getText(), secondName.getText(), password.getText());
//        System.out.println(player);
    }
    public void back(ActionEvent event) throws IOException {
        Stage stage = (Stage)username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        stage.setScene(new Scene(root, 1400, 800));
        stage.show();
    }
}
