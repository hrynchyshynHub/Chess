package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage stage;
    Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Socket s = new Socket("localhost", 5556);
//        String hello = "Hello ";
//        hello = hello + "\n" + s.getInetAddress().getHostAddress()
//                + ":" + s.getLocalPort();
//        s.getOutputStream().write(hello.getBytes());
//        byte buf[] = new byte[64 * 1024];
//        int r = s.getInputStream().read(buf);
//        String data = new String(buf, 0, r);
//        System.out.println(data);
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        primaryStage.setTitle("Chess");
        mainScene = new Scene(root, 1400, 800);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
