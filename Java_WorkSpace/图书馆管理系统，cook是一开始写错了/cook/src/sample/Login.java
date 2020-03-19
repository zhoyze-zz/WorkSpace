package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.*;

public class Login extends Application {

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("World");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
    public void main(String[] args) throws Exception {
//        //使用Connect对象连接
//        String url ="jdbc:sqlserver://localhost:1433;database=cook" ;
//        String uname ="sa" ;
//        String password = "rg715029";
//        Connect connect = new Connect(url,uname,password);
       launch(args);
    }
}
