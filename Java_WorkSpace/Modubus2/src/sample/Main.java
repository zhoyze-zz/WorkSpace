package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main extends Application {

    public static Stage stage;   //界面的静态变量布局

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 360, 550));
        stage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
       // CommUtil commUtil = CommUtil.getInstance();
        launch(args);
    }
}
