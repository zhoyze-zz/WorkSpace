package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public String unum;  //存放获取到的用户名
    public String pwd;   //存放获取到的密码
    public String kind;   //存放获取到的密码

    String url ="jdbc:sqlserver://localhost:1433;database=book" ;
    String uname ="sa" ;
    String password = "rg715029";
    LoginConnect loginConnect;


    @FXML private TextField yonghuming;  //连接登录界面的用户名
    @FXML private PasswordField pasword;  //连接登录界面的密码
    @FXML private ChoiceBox cbuserkind;

    @FXML
    public void loginclick() throws SQLException, IOException {

        unum = yonghuming.getText().toString();
        pwd =  pasword.getText().toString();
        kind = cbuserkind.getSelectionModel().getSelectedItem().toString(); //获取choicebox中当前选中的选项
        System.out.println(unum+pwd+kind);

        loginConnect = new LoginConnect(url,uname,password);  //创建一个连接数据库的对象
        Boolean iflogin = loginConnect.login(unum,pwd,kind);  //调用connect中的登录函数登录
        System.out.println(iflogin);
            if(iflogin&&kind.equals("学生")){ //当返回结果为true且时学生用户时，表示登录成功，跳转到student操作界面
                StudentController stu=new StudentController(unum);  //实现界面的跳转 创建一个studentcontroll界面的类
                stu.show();
                Main log = new Main();
                log.stage.hide();       //关闭登录界面
                }
            else if (iflogin&&kind.equals("图书管理员")){//当返回结果为true且是图书管理员用户时，表示登录成功，跳转到bookmanger操作界面
                BookmanagerController bm=new BookmanagerController(unum);  //实现界面的跳转 创建一个studentcontroll界面的类
                bm.show();
                Main log = new Main();
                log.stage.hide();       //关闭登录界面
            }
            else if (iflogin&&kind.equals("用户管理员")) {//当返回结果为true且是用户管理员用户时，表示登录成功，跳转到usermanger操作界面
                UsermanagerController um = new UsermanagerController(unum);  //实现界面的跳转 创建一个studentcontroll界面的类
                um.show();
                Main log = new Main();
                log.stage.hide();       //关闭登录界面
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING); //错误提示框
                alert.setTitle("提示！");
                alert.setHeaderText("用户名或密码错误");
                //alert.setContentText("用户名或密码错误");
                alert.showAndWait();
            }
        loginConnect.close();
    }


}

