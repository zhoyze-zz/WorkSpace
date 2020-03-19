package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Optional;

public class BookmanagerController {

    String url = "jdbc:sqlserver://localhost:1433;database=book";
    String uname = "sa";
    String password = "rg715029";
    BookmanagerConnect bookmanagerConnect = new BookmanagerConnect(url,uname,password); //创建数据库连接对象

    String selectbno = null;//用于保存选中的图书编号

    final ObservableList<usersearchbook> bookdata = FXCollections.observableArrayList();//定义observableArrayList用来存入从数据库获取到的数据集
    final ObservableList<userborrowbook> borrowdata = FXCollections.observableArrayList();//定义observableArrayList用来存入从数据库获取到的数据集

    private Stage stage = new Stage();
    private String BMnum = null;
    private String BMname = null;
    private String BMsalary= null;
    private String BMpswd = null;
    private String BMlib = null;
    private String BMphone = null;
    private String BMsex = null;

    @FXML
    private Label bmnum;    //显示学号（用户名）的label
    @FXML
    private Label bmname;    //显示学生姓名的label
    @FXML
    private Label bmsalary;    //显示学号（用户名）的label
    @FXML
    private Label bmphone;    //显示学生姓名的label
    @FXML
    private Label bmsex;    //显示学号（用户名）的label
    @FXML
    private Label bmlib;    //显示学生姓名的label

    @FXML
    private Label selectbook;    //显示选中的图书名称的label




    @FXML
    private TextField bookname;  //输入书名的TextField
    @FXML
    private ChoiceBox bookkind;  //图书种类下拉选择框
    @FXML
    private ChoiceBox searchkind; //查询方式选择框

    @FXML
    private TextField bookname2;  //输入书名的TextField
    @FXML
    private ChoiceBox bookkind2;  //图书种类下拉选择框
    @FXML
    private ChoiceBox searchkind2; //查询方式选择框


    @FXML
    private TableView <usersearchbook> usersearchtable;  //查找图书表
    @FXML
    private TableView<userborrowbook> userborrowtable; //借阅图书表
    @FXML
    private TableView<usersearchbook> managetable; //管理图书表

    @FXML
    private TableColumn Bno ;  //图书编号列表
    @FXML
    private TableColumn Bkind; //图书种类列表
    @FXML
    private TableColumn Bname; //图书名称列表
    @FXML
    private TableColumn Bprice; //图书价格列表
    @FXML
    private TableColumn Bstatue; //图书状态列表
    @FXML
    private TableColumn Bpub; //出版社列表
    @FXML
    private TableColumn Bauther; //图书作者列表
    @FXML
    private TableColumn Lname; //藏书处
    @FXML
    private TableColumn Bpubdate; //出版日期列表


    @FXML
    private TableColumn Bno2 ;  //图书编号列表
    @FXML
    private TableColumn Bkind2; //图书种类列表
    @FXML
    private TableColumn Bname2; //图书名称列表
    @FXML
    private TableColumn Bauther2; //图书名称列表
    @FXML
    private TableColumn Bpub2; //图书名称列表
    @FXML
    private TableColumn Bprice2; //图书价格列表
    @FXML
    private TableColumn Boutdate; //图书剩余数目列表
    @FXML
    private TableColumn Bindate; //图书剩余数目列表
    @FXML
    private TableColumn Snum; //学号列表
    @FXML
    private TableColumn Sname; //图书剩余数目列表


    @FXML
    private TableColumn Bno3 ;  //图书编号列表
    @FXML
    private TableColumn Bkind3; //图书种类列表
    @FXML
    private TableColumn Bname3; //图书名称列表
    @FXML
    private TableColumn Bprice3; //图书价格列表
    @FXML
    private TableColumn Bstatue3; //图书状态列表
    @FXML
    private TableColumn Bpub3; //出版社列表
    @FXML
    private TableColumn Bauther3; //图书作者列表
    @FXML
    private TableColumn Lname3; //藏书处
    @FXML
    private TableColumn Bpubdate3; //出版日期列表

    @FXML
    private ChoiceBox searchkind3; //查询方式选择框

    @FXML
    private TextField managebno;  //输入书号的TextField
    @FXML
    private TextField managebname;  //输入书名的TextField
    @FXML
    private TextField managebkind;  //输入书种类的TextField
    @FXML
    private TextField managebauther;  //输入书作者的TextField
    @FXML
    private TextField managebpub;  //输入出版社的TextField
    @FXML
    private TextField managebprice;  //输入书价格的TextField
    @FXML
    private TextField managebstatue;  //输入书状态的TextField
    @FXML
    private TextField managebpubdate;  //输入书出版日期的TextField
    @FXML
    private TextField manageLname;  //输入藏书馆的的TextField



    public BookmanagerController(String bmnum) throws IOException, SQLException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("bookmanager.fxml"));
        stage.setTitle("图书管理系统-图书管理员");
        stage.setResizable(false);
        root.setController(this); //设置连接controller
        stage.setScene(new Scene(root.load(), 800, 600));
        this.BMnum = bmnum;
        this.bmnum.setText(bmnum);
        bookmanagerConnect.connect(); //建立连接
        ResultSet rs = bookmanagerConnect.getBookmanagerInfo(bmnum);
        while(rs.next()){
            BMname = rs.getString(2);
            BMpswd = rs.getString(3);
            BMphone = rs.getString(4);
            BMsex = rs.getString(5);
            BMsalary = rs.getString(6);
            BMlib = rs.getString(7);
        }
        bmname.setText(BMname);
        bmphone.setText(BMphone);
        bmlib.setText(BMlib);
        bmsalary.setText(BMsalary);
        bmsex.setText(BMsex);

    }//构造函数

    public void show(){
        stage.show();
    }

    public void hide(){
        stage.hide();
    }

    @FXML
    public void BMsearchtableclick() throws SQLException {   //为tableview添加点击事件
        int index = usersearchtable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        System.out.println(bookdata.get(index).getBname());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setWidth(400);
        alert.setHeight(400);
        alert.setTitle("选择");
        alert.setHeaderText("查看书籍详细信息，点击删除按钮可删除该书");
        alert.setContentText("图书编号:\t"+bookdata.get(index).getBno()+
                "\n\n图书名称:\t"+bookdata.get(index).getBname()+
                "\n\n图书类别:\t"+bookdata.get(index).getBkind()+
                "\n\n图书作者:\t"+bookdata.get(index).getBauther()+
                "\n\n出 版 社:\t"+bookdata.get(index).getBpub()+
                "\n\n图书价格:\t"+bookdata.get(index).getBprice()+
                "\n\n出版日期:\t"+bookdata.get(index).getBpubdate()+
                "\n\n借阅状态:\t"+bookdata.get(index).getBstatue()+
                "\n\n藏书地址:\t"+bookdata.get(index).getLname());

        ButtonType buttonTypeOne = new ButtonType("删 除");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){//删除
            bookmanagerConnect.connect();  //建立连接
            int rs = bookmanagerConnect.Detelebook(bookdata.get(index).getBno(),bookdata.get(index).getBstatue());
            if(rs==1){ //当返回值为1时表示成功删除
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                alert1.setTitle("信息");
                alert1.setHeaderText("成功删除");
                alert1.showAndWait();
            }
            else {//否则该书被借阅中，删除失败
                Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                alert2.setTitle("提示！");
                alert2.setHeaderText("删除失败，该书被借阅中");
                alert2.showAndWait();
            }
            bookmanagerConnect.StudentConnClose();//关闭连接
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        bookmanagerConnect.StudentConnClose();
    }

    @FXML
    public void BMsearch() throws SQLException {   //为筛选按钮添加点击事件
        bookmanagerConnect.connect();  //建立连接
        String name;
        name = bookname.getText().toString();
        String skind;   //获取查询种类
        skind = searchkind.getSelectionModel().getSelectedItem().toString();
        String bbkind;   //获取书的种类
//        bbkind = bookkind.getSelectionModel().getSelectedItem().toString();
        System.out.println("press Search button");
        if (skind.equals("按书名查找")) {   //当选择按照书名查找时调用searchbyname函数
            ResultSet rs;
            rs = bookmanagerConnect.searchbyname(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bstatue.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            Bpubdate.setCellValueFactory(new PropertyValueFactory<>("Bpubdate"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            usersearchtable.setItems(bookdata);

            System.out.println("按书名查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按出版社查找")){ //当选择按照书名查找时调用searchbypublisher函数
            ResultSet rs;
            rs = bookmanagerConnect.searchbypublisher(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bstatue.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            Bpubdate.setCellValueFactory(new PropertyValueFactory<>("Bpubdate"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            usersearchtable.setItems(bookdata);

            System.out.println("按出版社查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按书类别查找")){ //当选择按照书名查找时调用searchbykind函数
            bbkind = bookkind.getSelectionModel().getSelectedItem().toString();
            ResultSet rs;
            rs = bookmanagerConnect.searchbykind(bbkind); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bstatue.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            Bpubdate.setCellValueFactory(new PropertyValueFactory<>("Bpubdate"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            usersearchtable.setItems(bookdata);

            System.out.println("按书类别查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按作者查找")){ //当选择按照书名查找时调用searchbykind函数
            ResultSet rs;
            rs = bookmanagerConnect.searchbyauther(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bstatue.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
            Bpubdate.setCellValueFactory(new PropertyValueFactory<>("Bpubdate"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            usersearchtable.setItems(bookdata);

            System.out.println("按作者查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
    }

    @FXML
    public void BMshowall() throws SQLException {
        bookmanagerConnect.connect();  //建立连接
        ResultSet rs;
        rs = bookmanagerConnect.showall(); //name为输入框输入的值
        //rs为获取到的查询数据集
        bookdata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
        }
        Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
        Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
        Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
        Bstatue.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
        Lname.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        Bpubdate.setCellValueFactory(new PropertyValueFactory<>("Bpubdate"));
        Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
        Bauther.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
        usersearchtable.setItems(bookdata);

        System.out.println("显示所有");
        bookmanagerConnect.StudentConnClose();//关闭连接
        System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
    }


    @FXML
    public void BMborrowsearch() throws SQLException { //当点击借阅信息界面中的筛选按钮时，显示借阅信息的函数
        bookmanagerConnect.connect();  //建立连接
        String name;
        name = bookname2.getText().toString();
        String skind;   //获取查询种类
        skind = searchkind2.getSelectionModel().getSelectedItem().toString();
        String bbkind;   //获取书的种类
//        bbkind = bookkind2.getSelectionModel().getSelectedItem().toString()+"hh";
        bbkind="asd";
        System.out.println("press Search button");
        if (skind.equals("按书名查找")) {   //当选择按照书名查找时调用searchbyname函数
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbyname(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);

            System.out.println("按书名查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按书类别查找")){ //当选择按照书名查找时调用searchbykind函数
            bbkind = bookkind2.getSelectionModel().getSelectedItem().toString();
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbykind(bbkind); //name为获取到的书的类别
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
                System.out.println(rs.getString(1)+ rs.getString(2)+ rs.getString(3)+ rs.getString(4)+ rs.getString(5)+ rs.getString(6)+ rs.getString(7)+rs.getString(8)+rs.getString(9)+rs.getString(10));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);

            System.out.println("按书类别查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按作者查找")) {   //当选择按照书名查找时调用searchbyname函数
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbyauther(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);

            System.out.println("按作者查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按学生学号查找")){ //当选择按照书名查找时调用searchbykind函数
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbySno(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);

            System.out.println("按学生学号查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按学生姓名查找")){ //当选择按照书名查找时调用searchbykind函数
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbySname(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);
            System.out.println("按学生姓名查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按应还日期查找")){ //当选择按照书名查找时调用searchbykind函数
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbyindate(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);
            System.out.println("按学生姓名查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按借出日期查找")){ //当选择按照书名查找时调用searchbykind函数
            ResultSet rs;
            rs = bookmanagerConnect.searchborrowbyoutdate(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            borrowdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
            Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
            Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
            Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
            Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
            Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
            userborrowtable.setItems(borrowdata);
            System.out.println("按借出日期查找");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
            bookmanagerConnect.StudentConnClose();//关闭连接
        }

    }

    @FXML
    public void BMborrowshowall() throws SQLException {
        bookmanagerConnect.connect();
        ResultSet rs;
        rs = bookmanagerConnect.showallborrow(); //name为输入框输入的值
        //rs为获取到的查询数据集
        borrowdata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
        }
        Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
        Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
        Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
        Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
        Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
        Snum.setCellValueFactory(new PropertyValueFactory<>("Snum"));
        Sname.setCellValueFactory(new PropertyValueFactory<>("Sname"));
        Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
        Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
        userborrowtable.setItems(borrowdata);
        System.out.println("显示所有");
        bookmanagerConnect.StudentConnClose();//关闭连接
        System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void BMborrowsearchtableclick() throws SQLException {   //为我的借阅tableview添加点击事件
        int index = userborrowtable.getSelectionModel().getSelectedIndex();  //获取当前点击的行数
        System.out.println(index); //获取当前选中的行数
        System.out.println(borrowdata.get(index).getBname());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setWidth(400);
        alert.setHeight(400);
        alert.setTitle("选择");
        alert.setHeaderText("请选择归还或是续借");
        alert.setContentText("图书编号:\t"+borrowdata.get(index).getBno()+
                "\n\n图书名称:\t"+borrowdata.get(index).getBname()+
                "\n\n图书类别:\t"+borrowdata.get(index).getBkind()+
                "\n\n学    号:\t"+borrowdata.get(index).getSnum()+
                "\n\n学生姓名:\t"+borrowdata.get(index).getSname()+
                "\n\n图书作者:\t"+borrowdata.get(index).getBauther()+
                "\n\n出 版 社:\t"+borrowdata.get(index).getBpub()+
                "\n\n图书价格:\t"+borrowdata.get(index).getBprice()+
                "\n\n借阅日期:\t"+borrowdata.get(index).getBdateout()+
                "\n\n应还日期:\t"+borrowdata.get(index).getBdatein());

        ButtonType buttonTypeOne = new ButtonType("归还");
        ButtonType buttonTypeTwo = new ButtonType("续借");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeTwo){//续借
            bookmanagerConnect.connect();  //建立连接
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
            c.add(Calendar.DATE, 20);
            int year = c.get(Calendar.YEAR);  //
            int month = c.get(Calendar.MONTH)+1;
            int day = c.get(Calendar.DATE);
            String indate = year+"-"+month+"-"+day;
            System.out.println(indate);
            int rs = bookmanagerConnect.Updateindate(borrowdata.get(index).getBno(),indate);
            if(rs==1){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                alert1.setTitle("信息");
                alert1.setHeaderText("成功续借\n\n借阅日期："+borrowdata.get(index).getBdateout()+"\n\n应还日期:"+indate);
                //alert.setContentText("用户名或密码错误");
                alert1.showAndWait();
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                alert2.setTitle("提示！");
                alert2.setHeaderText("续借失败");
                //alert.setContentText("用户名或密码错误");
                alert2.showAndWait();
            }
            bookmanagerConnect.StudentConnClose();//关闭连接
        } else if (result.get() == buttonTypeOne) { //归还
            bookmanagerConnect.connect();  //建立连接
            int flag = bookmanagerConnect.UpdateBookStatueTokejie(borrowdata.get(index).getBno()); //将图书表中该图书的状态修改为可借
            int flag1 = bookmanagerConnect.Deteleborrow(borrowdata.get(index).getBno()); //从借阅表中删除该条借阅记录
            if(flag==1&&flag1==1){
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION); //借阅失败提示框
                alert3.setTitle("信息");
                alert3.setHeaderText("还书成功");
                //alert.setContentText("用户名或密码错误");
                alert3.showAndWait();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        bookmanagerConnect.StudentConnClose();
    }

    @FXML
    public void BMmanageshowall() throws SQLException {
        bookmanagerConnect.connect();  //建立连接
        ResultSet rs;
        rs = bookmanagerConnect.showall(); //name为输入框输入的值
        //rs为获取到的查询数据集
        bookdata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
        }
        Bno3.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        Bkind3.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
        Bname3.setCellValueFactory(new PropertyValueFactory<>("Bname"));
        Bprice3.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
        Bstatue3.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
        Lname3.setCellValueFactory(new PropertyValueFactory<>("Lname"));
        Bpubdate3.setCellValueFactory(new PropertyValueFactory<>("Bpubdate"));
        Bpub3.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
        Bauther3.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
        managetable.setItems(bookdata);

        System.out.println("显示所有");
        bookmanagerConnect.StudentConnClose();//关闭连接
        System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void managetableclick() throws SQLException {   //为tableview添加点击事件
        int index = managetable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        selectbook.setText(bookdata.get(index).getBname());
        selectbno = bookdata.get(index).getBno();

        this.managebname.setText(bookdata.get(index).getBname());  //设置输入框中的值
        this.managebno.setText(bookdata.get(index).getBno());    //设置输入框中的值
        this.managebkind.setText(bookdata.get(index).getBkind()); //设置输入框中的值
        this.managebpub.setText(bookdata.get(index).getBpub()); //设置输入框中的值
        this.managebprice.setText(bookdata.get(index).getBprice()); //设置输入框中的值
        this.managebpubdate.setText(bookdata.get(index).getBpubdate()); //设置输入框中的值
        this.manageLname.setText(bookdata.get(index).getLname()); //设置输入框中的值
        this.managebauther.setText(bookdata.get(index).getBauther()); //设置输入框中的值
        this.managebstatue.setText(bookdata.get(index).getBstatue()); //设置输入框中的值

        System.out.println(bookdata.get(index).getBname());
    }

    @FXML
    public void BMmanagexecute() throws SQLException {
        bookmanagerConnect.connect();  //建立连接
        String managebname = this.managebname.getText();
        String managebno = this.managebno.getText();
        String managebkind = this.managebkind.getText();
        String managebpub = this.managebpub.getText();
        String managebprice = this.managebprice.getText();
        String managebpubdate = this.managebpubdate.getText();
        String manageLame = this.manageLname.getText();
        String managebauther = this.managebauther.getText();
        //String managebstatue = this.managebstatue.getText();

        String skind;   //获取操作的种类 增加或修改或删除
        skind = searchkind3.getSelectionModel().getSelectedItem().toString(); //获取操作的种类 增加或修改或删除
        System.out.println("press Search button");
        if (skind.equals("增加")) {   //当选择按照书名查找时调用searchbyname函数
            int rs;
            rs = bookmanagerConnect.Addbook(managebno,managebkind,managebname,managebauther,managebpub,managebprice,managebpubdate,manageLame); //name为输入框输入的值
            //rs为获取到的查询数据集
            if (rs==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION); //借阅失败提示框
                alert.setTitle("信息");
                alert.setHeaderText("添加成功");
                //alert.setContentText("用户名或密码错误");
                alert.showAndWait();
            }
            System.out.println("添加图书");
            bookmanagerConnect.StudentConnClose();//关闭连接
            System.out.println(managetable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("删除")){
            bookmanagerConnect.connect();  //建立连接
            int index = managetable.getSelectionModel().getSelectedIndex();
            System.out.println(index); //获取当前选中的行数
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("删除图书");
            alert.setHeaderText("请确认是否删除该图书");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int rs = bookmanagerConnect.Detelebook(bookdata.get(index).getBno(),bookdata.get(index).getBstatue());
                if(rs==1){ //当返回值为1时表示成功删除
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                    alert1.setTitle("信息");
                    alert1.setHeaderText("成功删除");
                    alert1.showAndWait();
                }
                else {//否则该书被借阅中，删除失败
                    Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                    alert2.setTitle("提示！");
                    alert2.setHeaderText("删除失败，该书被借阅中");
                    alert2.showAndWait();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }

            bookmanagerConnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("修改")){
            bookmanagerConnect.connect();  //建立连接
            int index = managetable.getSelectionModel().getSelectedIndex();
            System.out.println(index); //获取当前选中的行数

            managebname = this.managebname.getText(); //重新获取修改后的图书信息
            managebno = this.managebno.getText();
            managebkind = this.managebkind.getText();
            managebpub = this.managebpub.getText();
            managebprice = this.managebprice.getText();
            managebpubdate = this.managebpubdate.getText();
            manageLame = this.manageLname.getText();
            managebauther = this.managebauther.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("修改图书");
            alert.setHeaderText("请确认是否修改该图书");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int rs = bookmanagerConnect.alterbook(selectbno,managebno,managebkind,managebname,managebauther,managebpub,managebprice,managebpubdate,manageLame);
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
            bookmanagerConnect.StudentConnClose();//关闭连接
        }
    }

    public void relogin(){
        hide();
        Main log = new Main();
        log.stage.show();       //打开登录界面
    }

    public static class usersearchbook {     //创建一个usersearchbook类，用来存放从数据库中查询到的图书的信息
        private final SimpleStringProperty Bno;   //书号
        private final SimpleStringProperty Bkind;  //书种类
        private final SimpleStringProperty Bname;  //书名
        private final SimpleStringProperty Bauther; //书作者
        private final SimpleStringProperty Bpub;  //出版社
        private final SimpleStringProperty Bprice;  //价格
        private final SimpleStringProperty Bstatue;  //图书状态
        private final SimpleStringProperty Bpubdate;  //图书状态
        private final SimpleStringProperty Lname;  //藏书

        private usersearchbook(String bno, String bkind, String bname,String bauther,String bpub,String bprice,String bstatue,String bpubdate,String lname) {//构造函数
            this.Bno = new SimpleStringProperty(bno);
            this.Bkind = new SimpleStringProperty(bkind);
            this.Bname = new SimpleStringProperty(bname);
            this.Bauther = new SimpleStringProperty(bauther);
            this.Bpub = new SimpleStringProperty(bpub);
            this.Bprice = new SimpleStringProperty(bprice);
            this.Bstatue = new SimpleStringProperty(bstatue);
            this.Bpubdate = new SimpleStringProperty(bpubdate);
            this.Lname = new SimpleStringProperty(lname);
        }
        public String getBno() {  //获取书号
            return Bno.get();
        }
        public void setBno(String fName) {  //设置书号
            Bno.set(fName);
        }

        public String getBkind() {  //获取书种类
            return Bkind.get();
        }
        public void setBkind(String fName) {  //设置书种类
            Bkind.set(fName);
        }

        public String getBname() {  //获取书名
            return Bname.get();
        }
        public void setBname(String fName) {  //设置书名
            Bname.set(fName);
        }

        public String getBauther() {  //获取作者
            return Bauther.get();
        }
        public void setBauther(String fName) {  //设置作者
            Bauther.set(fName);
        }

        public String getBpub() {  //获取出版社
            return Bpub.get();
        }
        public void setBpub(String fName) {  //设置出版社
            Bpub.set(fName);
        }

        public String getBprice() {  //获取价格
            return Bprice.get();
        }
        public void setBprice(String fName) {  //设置价格
            Bprice.set(fName);
        }

        public String getBstatue() {  //获取剩余数量
            return Bstatue.get();
        }
        public void setBleft(String fName) {  //设置剩余数量
            Bstatue.set(fName);
        }

        public String getLname() {  //获取剩余数量
            return Lname.get();
        }
        public void setLname(String fName) {  //设置剩余数量
            Lname.set(fName);
        }

        public String getBpubdate() {  //获取剩余数量
            return Bpubdate.get();
        }
        public void setBppubdate(String fName) {  //设置剩余数量
            Bpubdate.set(fName);
        }

    }

    public static class userborrowbook {     //创建一个userborrowbook类，用来存放从数据库中查询到的图书的信息
        private final SimpleStringProperty Bno2;   //书号
        private final SimpleStringProperty Bkind2;  //书种类
        private final SimpleStringProperty Snum;  //学号
        private final SimpleStringProperty Sname;  //学生姓名
        private final SimpleStringProperty Bname2;  //书名
        private final SimpleStringProperty Bauther2; //书作者
        private final SimpleStringProperty Bpub2;  //出版社
        private final SimpleStringProperty Bprice2;  //价格
        private final SimpleStringProperty Bdateout;  //借出日期
        private final SimpleStringProperty Bdatein;  //应还日期


        private userborrowbook(String bno2, String bkind2, String snum,String sname,String bname2,String bauther2,String bpub2,String bprice2,String bdateout,String bdatein) {//构造函数
            this.Bno2 = new SimpleStringProperty(bno2);
            this.Bkind2 = new SimpleStringProperty(bkind2);
            this.Snum= new SimpleStringProperty(snum);
            this.Sname= new SimpleStringProperty(sname);
            this.Bname2 = new SimpleStringProperty(bname2);
            this.Bauther2 = new SimpleStringProperty(bauther2);
            this.Bpub2 = new SimpleStringProperty(bpub2);
            this.Bprice2 = new SimpleStringProperty(bprice2);
            this.Bdateout = new SimpleStringProperty(bdateout);
            this.Bdatein = new SimpleStringProperty(bdatein);
        }

        public String getBno() {  //获取书号
            return Bno2.get();
        }
        public void setBno(String fName) {  //设置书号
            Bno2.set(fName);
        }

        public String getSnum() {  //获取书种类
            return Snum.get();
        }
        public void setSnum(String fName) {  //设置书种类
            Snum.set(fName);
        }

        public String getSname() {  //获取书种类
            return Sname.get();
        }
        public void setSname(String fName) {  //设置书种类
            Sname.set(fName);
        }

        public String getBkind() {  //获取书种类
            return Bkind2.get();
        }
        public void setBkind(String fName) {  //设置书种类
            Bkind2.set(fName);
        }

        public String getBname() {  //获取书名
            return Bname2.get();
        }
        public void setBname(String fName) {  //设置书名
            Bname2.set(fName);
        }

        public String getBauther() {  //获取作者
            return Bauther2.get();
        }
        public void setBauther(String fName) {  //设置作者
            Bauther2.set(fName);
        }

        public String getBpub() {  //获取出版社
            return Bpub2.get();
        }
        public void setBpub(String fName) {  //设置出版社
            Bpub2.set(fName);
        }

        public String getBprice() {  //获取价格
            return Bprice2.get();
        }
        public void setBprice(String fName) {  //设置价格
            Bprice2.set(fName);
        }

        public String getBdateout() {  //获取借出日期
            return Bdateout.get();
        }
        public void setBdateout(String fName) {  //设置借出日期
            Bdateout.set(fName);
        }

        public String getBdatein() {  //获取应还日期
            return Bdatein.get();
        }
        public void setLname(String fName) {  //获取应还日期
            Bdatein.set(fName);
        }
    }


}
