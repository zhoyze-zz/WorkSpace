package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Optional;

public class UsermanagerController {

    String url = "jdbc:sqlserver://localhost:1433;database=book";
    String uname = "sa";
    String password = "rg715029";
    UsermanagerConnect usermanagerConnect = new UsermanagerConnect(url,uname,password); //创建数据库连接对象

    String selectSnum = null;//用于保存选中的图书编号

    final ObservableList<studentuser> studentdata = FXCollections.observableArrayList();//定义observableArrayList用来存入从数据库获取到的数据集
    final ObservableList<bookmanageuser> bookmanagedata = FXCollections.observableArrayList();//定义observableArrayList用来存入从数据库获取到的数据集

    private Stage stage = new Stage();
    private String UMnum = null;
    private String UMname = null;
    private String UMsalary= null;
    private String UMpswd = null;
    private String UMphone = null;
    private String UMsex = null;

    @FXML
    private Label umnum;    //显示学号（用户名）的label
    @FXML
    private Label umname;    //显示学生姓名的label
    @FXML
    private Label umsalary;    //显示学号（用户名）的label
    @FXML
    private Label umphone;    //显示学生姓名的label
    @FXML
    private Label umsex;    //显示学号（用户名）的label


    @FXML
    private Label selectusername;    //显示选中的图书名称的label



/////////////////////////////////////////////////学生信息页面
    @FXML
    private TextField bookname;  //输入内容的TextField
    @FXML
    private ChoiceBox searchkind;
    @FXML
    private ChoiceBox studentxueyuan; //查询方式选择框
    @FXML
    private ChoiceBox studentgrade; //查询方式选择框


    @FXML
    private ChoiceBox searchkind2; //查询方式选择框


    @FXML
    private ChoiceBox searchkind3; //查询方式选择框
    @FXML
    private TextField bookname2;  //输入书名的TextField
    @FXML
    private ChoiceBox bookmanagerlib;


    @FXML
    private ChoiceBox searchkind4; //查询方式选择框



    @FXML
    private TableView <studentuser> stuedentusertable;  //查找学生用户表
    @FXML
    private TableView<studentuser> managestudentusertable; //管理学生用户表

    @FXML
    private TableView<bookmanageuser> BMusertable;; //查找图书管理员表
    @FXML
    private TableView<bookmanageuser> manageBMusertable; //管理图书管理员表


    @FXML
    private TableColumn Snum ;  //学号列表
    @FXML
    private TableColumn Sname; //姓名列表
    @FXML
    private TableColumn Sgrade; //年级列表
    @FXML
    private TableColumn Spswd; //密码列表
    @FXML
    private TableColumn Sxueyuan; //学院列表
    @FXML
    private TableColumn Sphone; //电话列表
    @FXML
    private TableColumn Sbnum; //借阅数量列表
    @FXML
    private TableColumn Ssex; //性别


    @FXML
    private TableColumn Snum2 ;  //学号列表
    @FXML
    private TableColumn Sname2; //姓名列表
    @FXML
    private TableColumn Sgrade2; //年级列表
    @FXML
    private TableColumn Spswd2; //密码列表
    @FXML
    private TableColumn Sxueyuan2; //学院列表
    @FXML
    private TableColumn Sphone2; //电话列表
    @FXML
    private TableColumn Sbnum2; //借阅数量列表
    @FXML
    private TableColumn Ssex2; //性别


    @FXML
    private TableColumn BMnum ;  //图书管理员编号列表
    @FXML
    private TableColumn BMname; //图书管理员名字
    @FXML
    private TableColumn BMpswd; //密码
    @FXML
    private TableColumn BMphone; //电话
    @FXML
    private TableColumn BMsex; //性别
    @FXML
    private TableColumn BMsalary; //月薪
    @FXML
    private TableColumn Lname; //隶属图书馆


    @FXML
    private TableColumn BMnum2 ;  //图书管理员编号列表
    @FXML
    private TableColumn BMname2; //图书管理员名字
    @FXML
    private TableColumn BMpswd2; //密码
    @FXML
    private TableColumn BMphone2; //电话
    @FXML
    private TableColumn BMsex2; //性别
    @FXML
    private TableColumn BMsalary2; //月薪
    @FXML
    private TableColumn Lname2; //隶属图书馆


    @FXML
    private TextField managesnum;  //输入学号的TextField
    @FXML
    private TextField managesname;  //输入名字的TextField
    @FXML
    private TextField managesgrade;  //输入年级的TextField
    @FXML
    private TextField managespswd;  //输入密码的TextField
    @FXML
    private TextField managesxueyuan;  //输入学院的TextField
    @FXML
    private TextField managessex;  //性别的TextField
    @FXML
    private TextField managesborrownum;  //输入借书数量的TextField
    @FXML
    private TextField managesphone;  //输入电话号码的TextField


    /////
    @FXML
    private TextField managebmnum;  //输入编号的TextField
    @FXML
    private TextField managebmname;  //输入名字的TextField
    @FXML
    private TextField managebmpswd;  //输入密码的TextField
    @FXML
    private TextField managebmsalary;  //输入月薪的TextField
    @FXML
    private TextField managelname;  //输入图书馆名字的TextField
    @FXML
    private TextField managebmphone;  //电话号的TextField
    @FXML
    private TextField managebmsex;  //输入性别数量的TextField


    public UsermanagerController(String umnum) throws IOException, SQLException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("usermanager.fxml"));
        stage.setTitle("图书管理系统-用户管理员");
        stage.setResizable(false);
        root.setController(this); //设置连接controller
        stage.setScene(new Scene(root.load(), 800, 600));
        this.UMnum = umnum;
        this.umnum.setText(umnum);
        usermanagerConnect.connect(); //建立连接
        ResultSet rs = usermanagerConnect.getUsermanagerInfo(umnum);
        while(rs.next()){
            UMname = rs.getString(2);
            UMpswd = rs.getString(3);
            UMphone = rs.getString(4);
            UMsex = rs.getString(5);
            UMsalary = rs.getString(6);
        }
        umname.setText(UMname);
        umphone.setText(UMphone);
        umsalary.setText(UMsalary);
        umsex.setText(UMsex);
    }//构造函数

    public void show(){
        stage.show();
    }

    public void hide(){
        stage.hide();
    }


    //11111111111111111111111111111111111111111111111111111111111111111
    @FXML //查找界面中筛选用户
    public void UMstudentsearch() throws SQLException {   //为筛选按钮添加点击事件
        usermanagerConnect.connect();  //建立连接
        String name;
        name = bookname.getText();
        String skind;   //获取查询种类
        skind = searchkind.getSelectionModel().getSelectedItem().toString();
        String bbkind;   //获取书的种类
//        bbkind = bookkind.getSelectionModel().getSelectedItem().toString();
        System.out.println("press Search button");
        if (skind.equals("按学号查找")) {   //当选择按照书名查找时调用searchbyname函数
            ResultSet rs;
            rs = usermanagerConnect.searchbysunm(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            studentdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                studentdata.add(new studentuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
            }
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Sgrade.setCellValueFactory(new PropertyValueFactory<>("Sgrade"));
            Spswd.setCellValueFactory(new PropertyValueFactory<>("Spswd"));
            Sxueyuan.setCellValueFactory(new PropertyValueFactory<>("Sxueyuan"));
            Sphone.setCellValueFactory(new PropertyValueFactory<>("Sphone"));
            Sbnum.setCellValueFactory(new PropertyValueFactory<>("Sbnum"));
            Ssex.setCellValueFactory(new PropertyValueFactory<>("Ssex"));
            stuedentusertable.setItems(studentdata);

            System.out.println("按学号查找");
            usermanagerConnect.StudentConnClose();//关闭连接
            System.out.println(stuedentusertable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按姓名查找")){ //当选择按照书名查找时调用searchbypublisher函数
            ResultSet rs;
            rs = usermanagerConnect.searchbysname(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            studentdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                studentdata.add(new studentuser(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Sgrade.setCellValueFactory(new PropertyValueFactory<>("Sgrade"));
            Spswd.setCellValueFactory(new PropertyValueFactory<>("Spswd"));
            Sxueyuan.setCellValueFactory(new PropertyValueFactory<>("Sxueyuan"));
            Sphone.setCellValueFactory(new PropertyValueFactory<>("Sphone"));
            Sbnum.setCellValueFactory(new PropertyValueFactory<>("Sbnum"));
            Ssex.setCellValueFactory(new PropertyValueFactory<>("Ssex"));
            stuedentusertable.setItems(studentdata);
            System.out.println("按姓名查找");
            usermanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("综合查找")){ //当选择按照书名查找时调用searchbykind函数
            String xueyuan = studentxueyuan.getSelectionModel().getSelectedItem().toString();
            String grade = studentgrade.getSelectionModel().getSelectedItem().toString();
            ResultSet rs;
            rs = usermanagerConnect.searchbykind(xueyuan,grade); //name为输入框输入的值
            //rs为获取到的查询数据集
            studentdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                studentdata.add(new studentuser(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Sgrade.setCellValueFactory(new PropertyValueFactory<>("Sgrade"));
            Spswd.setCellValueFactory(new PropertyValueFactory<>("Spswd"));
            Sxueyuan.setCellValueFactory(new PropertyValueFactory<>("Sxueyuan"));
            Sphone.setCellValueFactory(new PropertyValueFactory<>("Sphone"));
            Sbnum.setCellValueFactory(new PropertyValueFactory<>("Sbnum"));
            Ssex.setCellValueFactory(new PropertyValueFactory<>("Ssex"));
            stuedentusertable.setItems(studentdata);
            System.out.println("按综合查找");
            usermanagerConnect.StudentConnClose();//关闭连接
        }

    }

    @FXML//查找界面中显示所有用户
    public void UMstudentshowall() throws SQLException {
        usermanagerConnect.connect();  //建立连接
        ResultSet rs;
        rs = usermanagerConnect.studentshowall(); //name为输入框输入的值
        //rs为获取到的查询数据集
        studentdata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            studentdata.add(new studentuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
        }
        Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
        Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        Sgrade.setCellValueFactory(new PropertyValueFactory<>("Sgrade"));
        Spswd.setCellValueFactory(new PropertyValueFactory<>("Spswd"));
        Sxueyuan.setCellValueFactory(new PropertyValueFactory<>("Sxueyuan"));
        Sphone.setCellValueFactory(new PropertyValueFactory<>("Sphone"));
        Sbnum.setCellValueFactory(new PropertyValueFactory<>("Sbnum"));
        Ssex.setCellValueFactory(new PropertyValueFactory<>("Ssex"));
        stuedentusertable.setItems(studentdata);

        System.out.println("显示所有");
        usermanagerConnect.StudentConnClose();//关闭连接
        System.out.println(stuedentusertable.getSelectionModel().getSelectedIndex());
    }

    @FXML  //查找界面中点击tableview点击事件
    public void UMstudentsearchtableclick() throws SQLException {   //为tableview添加点击事件
        int index = stuedentusertable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        System.out.println(studentdata.get(index).getSname());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setWidth(400);
        alert.setHeight(400);
        alert.setTitle("选择");
        alert.setHeaderText("查看用户详细信息，点击删除按钮可删除该用户");
        alert.setContentText("学  号:\t"+studentdata.get(index).getSnum()+
                "\n\n姓  名:\t"+studentdata.get(index).getSname()+
                "\n\n年  级:\t"+studentdata.get(index).getSgrade()+
                "\n\n密  码:\t"+studentdata.get(index).getSpswd()+
                "\n\n性  别:\t"+studentdata.get(index).getSsex()+
                "\n\n学  院:\t"+studentdata.get(index).getSxueyuan()+
                "\n\n借书量:\t"+studentdata.get(index).getSbnum()+
                "\n\n电  话:\t"+studentdata.get(index).getSphone());

        ButtonType buttonTypeOne = new ButtonType("删 除");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){//删除
            usermanagerConnect.connect();  //建立连接
            int rs = usermanagerConnect.DeteleStudent(studentdata.get(index).getSnum(),studentdata.get(index).getSbnum());
            if(rs==1){ //当返回值为1时表示成功删除
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                alert1.setTitle("信息");
                alert1.setHeaderText("成功删除");
                alert1.showAndWait();
            }
            else {//否则该书被借阅中，删除失败
                Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                alert2.setTitle("提示！");
                alert2.setHeaderText("删除失败，该用户有书未还");
                alert2.showAndWait();
            }
            usermanagerConnect.StudentConnClose();//关闭连接
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        usermanagerConnect.StudentConnClose();
    }
    //222222222222222222222222222222222222222222222222222222222222222222
    @FXML  //管理界面中显示所有学生用户信息
    public void UMstudentmanageshowall() throws SQLException {
        usermanagerConnect.connect();  //建立连接
        ResultSet rs;
        rs = usermanagerConnect.studentshowall(); //name为输入框输入的值
        //rs为获取到的查询数据集
        studentdata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            studentdata.add(new studentuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
        }
        Snum2.setCellValueFactory(new PropertyValueFactory<>("Snum"));
        Sname2.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        Sgrade2.setCellValueFactory(new PropertyValueFactory<>("Sgrade"));
        Spswd2.setCellValueFactory(new PropertyValueFactory<>("Spswd"));
        Sxueyuan2.setCellValueFactory(new PropertyValueFactory<>("Sxueyuan"));
        Sphone2.setCellValueFactory(new PropertyValueFactory<>("Sphone"));
        Sbnum2.setCellValueFactory(new PropertyValueFactory<>("Sbnum"));
        Ssex2.setCellValueFactory(new PropertyValueFactory<>("Ssex"));
        managestudentusertable.setItems(studentdata);

        System.out.println("显示所有");
        usermanagerConnect.StudentConnClose();//关闭连接
        System.out.println(managestudentusertable.getSelectionModel().getSelectedIndex());
    }

    @FXML   //管理界面中点击学生tableview点击事件
    public void UMstudentmanagetableclick(){
        int index = managestudentusertable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        selectusername.setText(studentdata.get(index).getSname());
        selectSnum = studentdata.get(index).getSnum();

        this.managesnum.setText(studentdata.get(index).getSnum());  //设置输入框中的值
        this.managesname.setText(studentdata.get(index).getSname());    //设置输入框中的值
        this.managesgrade.setText(studentdata.get(index).getSgrade()); //设置输入框中的值
        this.managesphone.setText(studentdata.get(index).getSphone()); //设置输入框中的值
        this.managespswd.setText(studentdata.get(index).getSpswd()); //设置输入框中的值
        this.managessex.setText(studentdata.get(index).getSsex()); //设置输入框中的值
        this.managesborrownum.setText(studentdata.get(index).getSbnum()); //设置输入框中的值
        this.managesxueyuan.setText(studentdata.get(index).getSxueyuan()); //设置输入框中的值

        System.out.println(studentdata.get(index).getSname());
    }

    @FXML  //管理界面中执行管理学生用户操作
    public void UMstudentmanagexecute() throws SQLException {
        usermanagerConnect.connect();  //建立连接
        String managesname = this.managesname.getText();
        String managesnum = this.managesnum.getText();
        String managessex = this.managessex.getText();
        String managespswd = this.managespswd.getText();
        String managesxueyuan = this.managesxueyuan.getText();
        String managesphone = this.managesphone.getText();
        String managesgrade = this.managesgrade.getText();
        String managesborrownum = this.managesborrownum.getText();

        String skind;   //获取操作的种类 增加或修改或删除
        skind = searchkind2.getSelectionModel().getSelectedItem().toString(); //获取操作的种类 增加或修改或删除
        System.out.println("press Search button");
        if (skind.equals("增加")) {   //当选择按照书名查找时调用searchbyname函数
            int rs;
            rs = usermanagerConnect.Addstudent(managesnum,managesname,managesgrade,managespswd,managesxueyuan,managessex,managesborrownum,managesphone); //name为输入框输入的值
            //rs为获取到的查询数据集
            if (rs==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION); //借阅失败提示框
                alert.setTitle("信息");
                alert.setHeaderText("添加成功");
                //alert.setContentText("用户名或密码错误");
                alert.showAndWait();
            }
            System.out.println("添加学生");
            usermanagerConnect.StudentConnClose();//关闭连接
            System.out.println(managestudentusertable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("删除")){
            usermanagerConnect.connect();  //建立连接
            int index = managestudentusertable.getSelectionModel().getSelectedIndex();
            System.out.println(index); //获取当前选中的行数
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("删除学生用户");
            alert.setHeaderText("请确认是否删除该学生");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int rs = usermanagerConnect.DeteleStudent(studentdata.get(index).getSnum(),studentdata.get(index).getSname());
                if(rs==1){ //当返回值为1时表示成功删除
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                    alert1.setTitle("信息");
                    alert1.setHeaderText("成功删除");
                    alert1.showAndWait();
                }
                else {//否则该书被借阅中，删除失败
                    Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                    alert2.setTitle("提示！");
                    alert2.setHeaderText("删除失败，该用户有书未还");
                    alert2.showAndWait();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }

            usermanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("修改")){
            usermanagerConnect.connect();  //建立连接
            int index = managestudentusertable.getSelectionModel().getSelectedIndex();
            System.out.println(index); //获取当前选中的行数

            managesname = this.managesname.getText();
            managesnum = this.managesnum.getText();
            managessex = this.managessex.getText();
            managespswd = this.managespswd.getText();
            managesxueyuan = this.managesxueyuan.getText();
            managesphone = this.managesphone.getText();
            managesgrade = this.managesgrade.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改用户");
            alert.setHeaderText("请确认是否修改该用户");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int rs = usermanagerConnect.alterstudent(selectSnum,managesnum,managesname,managesgrade,managespswd,managesxueyuan,managessex,managesborrownum,managesphone);
                if(rs==1){ //当返回值为1时表示成功删除
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                    alert1.setTitle("信息");
                    alert1.setHeaderText("修改成功");
                    alert1.showAndWait();
                }
                else {//否则该书被借阅中，删除失败
                    Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                    alert2.setTitle("提示！");
                    alert2.setHeaderText("修改失败");
                    alert2.showAndWait();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            usermanagerConnect.StudentConnClose();//关闭连接
        }
    }
    //33333333333333333333333333333333333333333333333333333333333333333

    @FXML //筛选图书管理员
    public void UMbookmanagesearch() throws SQLException{
        usermanagerConnect.connect();  //建立连接
        String name;
        name = bookname2.getText();
        String skind;   //获取查询种类
        skind = searchkind3.getSelectionModel().getSelectedItem().toString();
        String bbkind;   //获取书的种类
//        bbkind = bookkind.getSelectionModel().getSelectedItem().toString();
        System.out.println("press Search button");
        if (skind.equals("按编号查找")) {   //当选择按照书名查找时调用searchbyname函数
            ResultSet rs;
            rs = usermanagerConnect.searchbybmnum(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookmanagedata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookmanagedata.add(new bookmanageuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            BMnum.setCellValueFactory(new PropertyValueFactory<>("BMnum"));
            BMname.setCellValueFactory(new PropertyValueFactory<>("BMname"));
            BMpswd.setCellValueFactory(new PropertyValueFactory<>("BMpswd"));
            BMphone.setCellValueFactory(new PropertyValueFactory<>("BMphone"));
            BMsalary.setCellValueFactory(new PropertyValueFactory<>("BMsalary"));
            BMsex.setCellValueFactory(new PropertyValueFactory<>("BMsex"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            BMusertable.setItems(bookmanagedata);

            System.out.println("按编号号查找");
            usermanagerConnect.StudentConnClose();//关闭连接
            System.out.println(stuedentusertable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按姓名查找")){ //当选择按照书名查找时调用searchbypublisher函数
            ResultSet rs;
            rs = usermanagerConnect.searchbybmname(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookmanagedata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookmanagedata.add(new bookmanageuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            BMnum.setCellValueFactory(new PropertyValueFactory<>("BMnum"));
            BMname.setCellValueFactory(new PropertyValueFactory<>("BMname"));
            BMpswd.setCellValueFactory(new PropertyValueFactory<>("BMpswd"));
            BMphone.setCellValueFactory(new PropertyValueFactory<>("BMphone"));
            BMsalary.setCellValueFactory(new PropertyValueFactory<>("BMsalary"));
            BMsex.setCellValueFactory(new PropertyValueFactory<>("BMsex"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            BMusertable.setItems(bookmanagedata);
            System.out.println("按姓名查找");
            usermanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按图书馆查找")){ //当选择按照书名查找时调用searchbykind函数
            String libname = bookmanagerlib.getSelectionModel().getSelectedItem().toString();
            ResultSet rs;
            rs = usermanagerConnect.searchbylib(libname); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookmanagedata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookmanagedata.add(new bookmanageuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            BMnum.setCellValueFactory(new PropertyValueFactory<>("BMnum"));
            BMname.setCellValueFactory(new PropertyValueFactory<>("BMname"));
            BMpswd.setCellValueFactory(new PropertyValueFactory<>("BMpswd"));
            BMphone.setCellValueFactory(new PropertyValueFactory<>("BMphone"));
            BMsalary.setCellValueFactory(new PropertyValueFactory<>("BMsalary"));
            BMsex.setCellValueFactory(new PropertyValueFactory<>("BMsex"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            BMusertable.setItems(bookmanagedata);
            System.out.println("按图书馆名查找");
            usermanagerConnect.StudentConnClose();//关闭连接
        }
    }

    @FXML //显示所有图书管理员
    public void UMbookmanageshowall() throws SQLException{
        usermanagerConnect.connect();  //建立连接
        ResultSet rs;
        rs = usermanagerConnect.bookmanageshowall(); //name为输入框输入的值
        //rs为获取到的查询数据集
        bookmanagedata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            bookmanagedata.add(new bookmanageuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        BMnum.setCellValueFactory(new PropertyValueFactory<>("BMnum"));
        BMname.setCellValueFactory(new PropertyValueFactory<>("BMname"));
        BMpswd.setCellValueFactory(new PropertyValueFactory<>("BMpswd"));
        BMphone.setCellValueFactory(new PropertyValueFactory<>("BMphone"));
        BMsalary.setCellValueFactory(new PropertyValueFactory<>("BMsalary"));
        BMsex.setCellValueFactory(new PropertyValueFactory<>("BMsex"));
        Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        BMusertable.setItems(bookmanagedata);

        System.out.println("显示所有");
        usermanagerConnect.StudentConnClose();//关闭连接
        System.out.println(managestudentusertable.getSelectionModel().getSelectedIndex());
    }

    @FXML //图书管理员搜寻tableview点击事件
    public void UMbookmanagesearchtableclick() throws SQLException{
        int index = BMusertable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        System.out.println(bookmanagedata.get(index).getBMname());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setWidth(400);
        alert.setHeight(400);
        alert.setTitle("选择");
        alert.setHeaderText("查看用户详细信息，点击删除按钮可删除该用户");
        alert.setContentText(
                "\n\n编  号  :\t"+bookmanagedata.get(index).getBMnum()+
                "\n\n姓  名:\t"+bookmanagedata.get(index).getBMname()+
                "\n\n密  码:\t"+bookmanagedata.get(index).getBMpswd()+
                "\n\n性  别:\t"+bookmanagedata.get(index).getBMsex()+
                "\n\n月  薪:\t"+bookmanagedata.get(index).getBMsalary()+
                "\n\n图书馆:\t"+bookmanagedata.get(index).getLname()+
                "\n\n电  话:\t"+bookmanagedata.get(index).getBMphone());

        ButtonType buttonTypeOne = new ButtonType("删 除");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){//删除
            usermanagerConnect.connect();  //建立连接
            int rs = usermanagerConnect.Detelebookmanager(bookmanagedata.get(index).getBMnum());
            if(rs==1){ //当返回值为1时表示成功删除
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                alert1.setTitle("信息");
                alert1.setHeaderText("成功删除");
                alert1.showAndWait();
            }
            else {//否则该书被借阅中，删除失败
                Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                alert2.setTitle("提示！");
                alert2.setHeaderText("删除失败");
                alert2.showAndWait();
            }
            usermanagerConnect.StudentConnClose();//关闭连接
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        usermanagerConnect.StudentConnClose();
    }

    //44444444444444444444444444444444444444444444444444444444444444
    @FXML //图书管理员管理执行点击事件
    public void UMbookmanagexecute() throws SQLException{
        usermanagerConnect.connect();  //建立连接
        String managebmname = this.managebmname.getText();
        String managebmnum = this.managebmnum.getText();
        String managebmsex = this.managebmsex.getText();
        String managebmpswd = this.managebmpswd.getText();
        String managebmsalary = this.managebmsalary.getText();
        String managebmphone = this.managebmphone.getText();
        String managelname = this.managelname.getText();

        String skind;   //获取操作的种类 增加或修改或删除
        skind = searchkind4.getSelectionModel().getSelectedItem().toString(); //获取操作的种类 增加或修改或删除
        System.out.println("press Search button");
        if (skind.equals("增加")) {   //当选择按照书名查找时调用searchbyname函数
            int rs;
            rs = usermanagerConnect.Addbokmanager(managebmnum,managebmname,managebmpswd,managebmphone,managebmsex,managebmsalary,managelname); //name为输入框输入的值
            //rs为获取到的查询数据集
            if (rs==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION); //借阅失败提示框
                alert.setTitle("信息");
                alert.setHeaderText("添加成功");
                //alert.setContentText("用户名或密码错误");
                alert.showAndWait();
            }
            System.out.println("添加图书管理员");
            usermanagerConnect.StudentConnClose();//关闭连接
            System.out.println(managestudentusertable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("删除")){
            usermanagerConnect.connect();  //建立连接
            int index = manageBMusertable.getSelectionModel().getSelectedIndex();
            System.out.println(index); //获取当前选中的行数
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("删除图书管理员");
            alert.setHeaderText("请确认是否删除该图书管理员");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int rs = usermanagerConnect.Detelebookmanager(bookmanagedata.get(index).getBMnum());
                if(rs==1){ //当返回值为1时表示成功删除
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                    alert1.setTitle("信息");
                    alert1.setHeaderText("成功删除");
                    alert1.showAndWait();
                }
                else {//否则该书被借阅中，删除失败
                    Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                    alert2.setTitle("提示！");
                    alert2.setHeaderText("删除失败");
                    alert2.showAndWait();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }

            usermanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("修改")){
            usermanagerConnect.connect();  //建立连接
            int index = managestudentusertable.getSelectionModel().getSelectedIndex();
            System.out.println(index); //获取当前选中的行数

            managebmname = this.managebmname.getText();
            managebmnum = this.managebmnum.getText();
            managebmsex = this.managebmsex.getText();
            managebmpswd = this.managebmpswd.getText();
            managebmsalary = this.managebmsalary.getText();
            managebmphone = this.managebmphone.getText();
            managelname = this.managelname.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改用户");
            alert.setHeaderText("请确认是否修改该用户");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int rs = usermanagerConnect.alterbookmanage(selectSnum,managebmnum,managebmname,managebmpswd,managebmphone,managebmsex,managebmsalary,managelname);
                if(rs==1){ //当返回值为1时表示成功删除
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                    alert1.setTitle("信息");
                    alert1.setHeaderText("修改成功");
                    alert1.showAndWait();
                }
                else {//否则该书被借阅中，删除失败
                    Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                    alert2.setTitle("提示！");
                    alert2.setHeaderText("修改失败");
                    alert2.showAndWait();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
            usermanagerConnect.StudentConnClose();//关闭连接
        }

    }

    @FXML //管理显示所有
    public void UMbookmanageshowall2() throws SQLException{
        usermanagerConnect.connect();  //建立连接
        ResultSet rs;
        rs = usermanagerConnect.bookmanageshowall(); //name为输入框输入的值
        //rs为获取到的查询数据集
        bookmanagedata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            bookmanagedata.add(new bookmanageuser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
        }
        BMnum2.setCellValueFactory(new PropertyValueFactory<>("BMnum"));
        BMname2.setCellValueFactory(new PropertyValueFactory<>("BMname"));
        BMpswd2.setCellValueFactory(new PropertyValueFactory<>("BMpswd"));
        BMphone2.setCellValueFactory(new PropertyValueFactory<>("BMphone"));
        BMsalary2.setCellValueFactory(new PropertyValueFactory<>("BMsalary"));
        BMsex2.setCellValueFactory(new PropertyValueFactory<>("BMsex"));
        Lname2.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        manageBMusertable.setItems(bookmanagedata);

        System.out.println("m显示所有");
        usermanagerConnect.StudentConnClose();//关闭连接
        System.out.println(managestudentusertable.getSelectionModel().getSelectedIndex());
    }

    @FXML //图书管理员管理tableview点击事件
    public void UMbookmanagemanagetableclick() throws SQLException{
        int index = manageBMusertable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        selectusername.setText(bookmanagedata.get(index).getBMname());
        selectSnum = bookmanagedata.get(index).getBMnum();

        this.managebmnum.setText(bookmanagedata.get(index).getBMnum());  //设置输入框中的值
        this.managebmname.setText(bookmanagedata.get(index).getBMname());    //设置输入框中的值
        this.managebmpswd.setText(bookmanagedata.get(index).getBMpswd()); //设置输入框中的值
        this.managebmphone.setText(bookmanagedata.get(index).getBMphone()); //设置输入框中的值
        this.managebmsalary.setText(bookmanagedata.get(index).getBMsalary()); //设置输入框中的值
        this.managebmsex.setText(bookmanagedata.get(index).getBMsex()); //设置输入框中的值
        this.managelname.setText(bookmanagedata.get(index).getLname()); //设置输入框中的值

        System.out.println(bookmanagedata.get(index).getBMname());
    }




    @FXML   //重新登录
    public void relogin(){
        hide();
        Main log = new Main();
        log.stage.show();       //打开登录界面
    }

    public static class studentuser {     //创建一个usersearchbook类，用来存放从数据库中查询到的图书的信息
        private final SimpleStringProperty Snum;   //学生学号
        private final SimpleStringProperty Sname;  //学生姓名
        private final SimpleStringProperty Sgrade;  //年级
        private final SimpleStringProperty Spswd; //密码
        private final SimpleStringProperty Sxueyuan;  //学院
        private final SimpleStringProperty Sphone;  //电话
        private final SimpleStringProperty Sborrownum;  //借书数量
        private final SimpleStringProperty Ssex;  //性别

        private studentuser(String snum, String sname, String sgrade,String spswd,String sxueyuan,String sphone,String sborrownum,String ssex) {//构造函数
            this.Snum = new SimpleStringProperty(snum);
            this.Sname = new SimpleStringProperty(sname);
            this.Sgrade = new SimpleStringProperty(sgrade);
            this.Spswd = new SimpleStringProperty(spswd);
            this.Sxueyuan = new SimpleStringProperty(sxueyuan);
            this.Sphone = new SimpleStringProperty(sphone);
            this.Sborrownum = new SimpleStringProperty(sborrownum);
            this.Ssex = new SimpleStringProperty(ssex);
        }
        public String getSnum() {  //获取书号
            return Snum.get();
        }
        public void setSnum(String fName) {  //设置书号
            Snum.set(fName);
        }

        public String getSname() {  //获取书种类
            return Sname.get();
        }
        public void setSname(String fName) {  //设置书种类
            Sname.set(fName);
        }

        public String getSgrade() {  //获取书名
            return Sgrade.get();
        }
        public void setSgrade(String fName) {  //设置书名
            Sgrade.set(fName);
        }

        public String getSpswd() {  //获取作者
            return Spswd.get();
        }
        public void setSpswd(String fName) {  //设置作者
            Spswd.set(fName);
        }

        public String getSxueyuan() {  //获取出版社
            return Sxueyuan.get();
        }
        public void setSxueyuan(String fName) {  //设置出版社
            Sxueyuan.set(fName);
        }

        public String getSphone() {  //获取价格
            return Sphone.get();
        }
        public void setSphone(String fName) {  //设置价格
            Sphone.set(fName);
        }

        public String getSbnum() {  //获取剩余数量
            return Sborrownum.get();
        }
        public void setSbnum(String fName) {  //设置剩余数量
            Sborrownum.set(fName);
        }

        public String getSsex() {  //获取剩余数量
            return Ssex.get();
        }
        public void setSsex(String fName) {  //设置剩余数量
            Ssex.set(fName);
        }
    }

    public static class bookmanageuser {     //创建一个userborrowbook类，用来存放从数据库中查询到的图书的信息
        private final SimpleStringProperty BMnum;   //书号
        private final SimpleStringProperty BMname;  //书种类
        private final SimpleStringProperty BMpswd;  //学号
        private final SimpleStringProperty BMphone;  //学生姓名
        private final SimpleStringProperty BMsex;  //书名
        private final SimpleStringProperty BMsalary; //书作者
        private final SimpleStringProperty Lname;  //出版社

        private bookmanageuser(String bmnum, String bmname, String bmpswd,String bmphone,String bmsex,String bmsalary,String lname) {//构造函数
            this.BMnum = new SimpleStringProperty(bmnum);
            this.BMname = new SimpleStringProperty(bmname);
            this.BMpswd= new SimpleStringProperty(bmpswd);
            this.BMphone= new SimpleStringProperty(bmphone);
            this.BMsex = new SimpleStringProperty(bmsex);
            this.BMsalary = new SimpleStringProperty(bmsalary);
            this.Lname = new SimpleStringProperty(lname);
        }

        public String getBMnum() {  //获取书号
            return BMnum.get();
        }
        public void setBMnum(String fName) {  //设置书号
            BMnum.set(fName);
        }

        public String getBMname() {  //获取书种类
            return BMname.get();
        }
        public void setBMname(String fName) {  //设置书种类
            BMname.set(fName);
        }

        public String getBMpswd() {  //获取书种类
            return BMpswd.get();
        }
        public void setBMpswd(String fName) {  //设置书种类
            BMpswd.set(fName);
        }

        public String getBMphone() {  //获取书种类
            return BMphone.get();
        }
        public void setBMphone(String fName) {  //设置书种类
            BMphone.set(fName);
        }

        public String getBMsex() {  //获取书名
            return BMsex.get();
        }
        public void setBMsex(String fName) {  //设置书名
            BMsex.set(fName);
        }

        public String getBMsalary() {  //获取作者
            return BMsalary.get();
        }
        public void setBMsalary(String fName) {  //设置作者
            BMsalary.set(fName);
        }

        public String getLname() {  //获取出版社
            return Lname.get();
        }
        public void setLname(String fName) {  //设置出版社
            Lname.set(fName);
        }

    }
}
