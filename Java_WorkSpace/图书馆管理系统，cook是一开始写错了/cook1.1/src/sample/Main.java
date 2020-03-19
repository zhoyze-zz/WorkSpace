package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    private Parent root;
    public static Stage stage;   //界面的静态变量布局

    @FXML
    ChoiceBox cbuserkind;

    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setTitle("图书管理系统登录");
        stage.setScene(new Scene(root, 637, 393));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);//使用Connect对象连接
    }
}
