package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.lang.String.*;

public class StudentController {

    String url = "jdbc:sqlserver://localhost:1433;database=book";
    String uname = "sa";
    String password = "rg715029";
    StudentConnect studentconnect = new StudentConnect(url,uname,password);

    final ObservableList<usersearchbook> bookdata = FXCollections.observableArrayList();//定义observableArrayList用来存入从数据库获取到的数据集
    final ObservableList<userborrowbook> borrowdata = FXCollections.observableArrayList();//定义observableArrayList用来存入从数据库获取到的数据集

    private Stage stage = new Stage();
    private String Snum = null;
    private String Sname = null;
    private String Sgrade = null;
    private String Spswd = null;
    private String Sxueyuan = null;
    private String Sphone = null;
    private String Sborrownum = null;
    private String Ssex = null;

    @FXML
    private Label snum;    //显示学号（用户名）的label
    @FXML
    private Label sname;    //显示学生姓名的label
    @FXML
    private Label sgrade;    //显示学号（用户名）的label
    @FXML
    private Label sphone;    //显示学生姓名的label
    @FXML
    private Label sxueyuan;    //显示学号（用户名）的label
    @FXML
    private Label sborrownum;    //显示学生姓名的label
    @FXML
    private TextField bookname;  //输入书名的TextField
    @FXML
    private TextField pricelow;  //输入书名的TextField
    @FXML
    private TextField pricehigh;  //输入书名的TextField
    @FXML
    private ChoiceBox bookkind;  //图书种类下拉选择框
    @FXML
    private ChoiceBox searchkind; //查询方式选择框
    @FXML
    private ChoiceBox libkind;  //图书种类下拉选择框
    @FXML
    private ChoiceBox pubkind; //查询方式选择框
    @FXML
    private ChoiceBox statuekind; //查询方式选择框

    @FXML
    private TableView <usersearchbook> usersearchtable;  //查找图书表
    @FXML
    private TableView<userborrowbook> userborrowtable; //借阅图书表
    @FXML
    private TableColumn Bno ;  //图书编号列表
    @FXML
    private TableColumn Bkind; //图书种类列表
    @FXML
    private TableColumn Bname; //图书名称列表
    @FXML
    private TableColumn Bprice; //图书价格列表
    @FXML
    private TableColumn Bpub; //图书出版社列表
    @FXML
    private TableColumn Bleft; //图书剩余数目列表


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






    public StudentController(String snum) throws IOException, SQLException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("student.fxml"));
        stage.setTitle("图书管理系统-学生");
        stage.setResizable(false);
        root.setController(this); //设置连接controller
        stage.setScene(new Scene(root.load(), 800, 600));
        this.Snum = snum;
        this.snum.setText(Snum);
        studentconnect.connect(); //建立连接
        ResultSet rs = studentconnect.getStudentInfo(snum);
        while(rs.next()){
            Sname = rs.getString(2);
            Sgrade = rs.getString(3);
            Spswd = rs.getString(4);
            Sxueyuan = rs.getString(5);
            Sphone = rs.getString(6);
            Sborrownum = rs.getString(7);
            Ssex = rs.getString(8);
        }
        sname.setText(Sname);
        sgrade.setText(Sgrade);
        sphone.setText(Sphone);
        sxueyuan.setText(Sxueyuan);
        sborrownum.setText(Sborrownum);


    }//构造函数

    public void mydateclick() throws SQLException {
        studentconnect.connect(); //建立连接
        ResultSet rs = studentconnect.getStudentInfo(Snum);
        while(rs.next()){
            Sname = rs.getString(2);
            Sgrade = rs.getString(3);
            Spswd = rs.getString(4);
            Sxueyuan = rs.getString(5);
            Sphone = rs.getString(6);
            Sborrownum = rs.getString(7);
            Ssex = rs.getString(8);
        }
        sname.setText(Sname);
        sgrade.setText(Sgrade);
        sphone.setText(Sphone);
        sxueyuan.setText(Sxueyuan);
        sborrownum.setText(Sborrownum);

    }
    public void show(){
        stage.show();
    }
    public void hide(){
        stage.hide();
    }

    @FXML
    public void Ssearch() throws SQLException {   //为查询按钮添加点击事件
        studentconnect= new StudentConnect(url,uname,password);
        studentconnect.connect();  //建立连接
        String name;
        name = bookname.getText().toString();
        String skind;
        skind = searchkind.getSelectionModel().getSelectedItem().toString();
        String bbkind;
        bbkind = bookkind.getSelectionModel().getSelectedItem().toString();
        System.out.println("press Search button");
        if (skind.equals("按书名查找")) {   //当选择按照书名查找时调用searchbyname函数
            ResultSet rs;
            rs = studentconnect.searchbyname(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bleft.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            usersearchtable.setItems(bookdata);

            System.out.println("按书名查找");
            studentconnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
        }
        else if (skind.equals("按出版社查找")){ //当选择按照书名查找时调用searchbypublisher函数
            ResultSet rs;
            rs = studentconnect.searchbypublisher(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bleft.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            usersearchtable.setItems(bookdata);
            System.out.println("按出版社查找");
            studentconnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按书类别查找")){ //当选择按照书名查找时调用searchbykind函数
            bbkind = bookkind.getSelectionModel().getSelectedItem().toString();
            ResultSet rs;
            rs = studentconnect.searchbykind(bbkind); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bleft.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            usersearchtable.setItems(bookdata);
            System.out.println("按书类别查找");
            studentconnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("按作者查找")){ //当选择按照书名查找时调用searchbykind函数
            ResultSet rs;
            rs = studentconnect.searchbyauther(name); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bleft.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            usersearchtable.setItems(bookdata);
            System.out.println("按作者查找");
            studentconnect.StudentConnClose();//关闭连接
        }
        else if (skind.equals("高级查找")) {

//            @FXML
//            private ChoiceBox bookkind;  //图书种类下拉选择框
//            @FXML
//            private ChoiceBox searchkind; //查询方式选择框
//            @FXML
//            private ChoiceBox libkind;  //图书种类下拉选择框
//            @FXML
//            private ChoiceBox pubkind; //查询方式选择框
//            @FXML
//            private ChoiceBox statuekind; //查询方式选择框

            String bookkind2 = "不限";
            String pubkind2 = "不限";
            String libkind2 = "不限";
            String pricelow2 = "不限";
            String pricehigh2 = "不限";
            String statuekind2 = "不限";

            bookkind2 = bookkind.getSelectionModel().getSelectedItem().toString();
            pubkind2 =  pubkind.getSelectionModel().getSelectedItem().toString();
            libkind2 =  libkind.getSelectionModel().getSelectedItem().toString();
            pricelow2 =  pricelow.getText();
            pricehigh2 =  pricehigh.getText();                                          //
            statuekind2 =  statuekind.getSelectionModel().getSelectedItem().toString(); //书籍是否可借

            ResultSet rs;
            rs = studentconnect.searchbyzonghe(bookkind2,pubkind2,libkind2,pricelow2,pricehigh2,statuekind2); //name为输入框输入的值
            //rs为获取到的查询数据集
            bookdata.clear();
            while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
                bookdata.add(new usersearchbook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9)));
                System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7));

            }
            Bno.setCellValueFactory(new PropertyValueFactory<>("Bno"));
            Bkind.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
            Bname.setCellValueFactory(new PropertyValueFactory<>("Bname"));
            Bpub.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
            Bprice.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
            Bleft.setCellValueFactory(new PropertyValueFactory<>("Bstatue"));
            usersearchtable.setItems(bookdata);

            System.out.println("按高级查找");
            studentconnect.StudentConnClose();//关闭连接
            System.out.println(usersearchtable.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void usersearchtableclick() throws SQLException {   //为tableview添加点击事件
        int index = usersearchtable.getSelectionModel().getSelectedIndex();
        System.out.println(index); //获取当前选中的行数
        System.out.println(bookdata.get(index).getBname());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeight(400);
        alert.setWidth(400);
        alert.setTitle("图书详细信息");
        alert.setHeaderText("您想借这本书吗");
        alert.setContentText("图书编号:\t"+bookdata.get(index).getBno()+
                            "\n\n图书名称:\t"+bookdata.get(index).getBname()+
                            "\n\n图书类别:\t"+bookdata.get(index).getBkind()+
                            "\n\n图书作者:\t"+bookdata.get(index).getBauther()+
                            "\n\n出 版 社:\t"+bookdata.get(index).getBpub()+
                            "\n\n图书价格:\t"+bookdata.get(index).getBprice()+
                            "\n\n出版日期:\t"+bookdata.get(index).getBpubdate()+
                            "\n\n借阅状态:\t"+bookdata.get(index).getBstatue()+
                            "\n\n藏书地址:\t"+bookdata.get(index).getLname());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){    //当点击确认按钮时借书
            studentconnect.connect();
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
            int year = c.get(Calendar.YEAR);  //
            int month = c.get(Calendar.MONTH)+1;
            int day = c.get(Calendar.DATE);
            String outdate = year+"-"+month+"-"+day;
            System.out.println(outdate);
            c.add(Calendar.DATE, 10);
            int year2 = c.get(Calendar.YEAR);
            int month2 = c.get(Calendar.MONTH)+1;
            int day2 = c.get(Calendar.DATE);
            String indate= year2+"-"+month2+"-"+day2;
            int flag = studentconnect.UpdateBookStatueTojiechu(this.Snum,bookdata.get(index).getBno(),bookdata.get(index).getBstatue(),"借出");

            if(flag==1){
                studentconnect.InsertBorrow(bookdata.get(index).getBno(),
                        Snum,
                        Sname,
                        bookdata.get(index).getBpub(),
                        bookdata.get(index).getBprice(),
                        outdate,
                        indate,
                        bookdata.get(index).getBauther());

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION); //借阅成功提示框
                alert1.setTitle("信息");
                alert1.setHeaderText("成功借出\n\n借阅日期："+outdate+"\n\n应还日期:"+indate);
                //alert.setContentText("用户名或密码错误");
                alert1.showAndWait();
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.WARNING); //借阅失败提示框
                alert2.setTitle("提示！");
                alert2.setHeaderText("借阅失败，该书已借出");
                //alert.setContentText("用户名或密码错误");
                alert2.showAndWait();
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        studentconnect.StudentConnClose();
    }


    @FXML
    public void myborrow() throws SQLException { //当点击我的借阅显示我的借阅信息的函数
        studentconnect.connect();  //建立连接
        ResultSet rs = studentconnect.searchmyborrow(Snum);
        borrowdata.clear();
        while (rs.next()) {//循环将从数据库中读取到的数据添加到data中
            borrowdata.add(new userborrowbook(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            System.out.println(rs.getString(1)+rs.getString(2)+ rs.getString(3)+rs.getString(4)+rs.getString(5)+rs.getString(6)+rs.getString(7)+rs.getString(8));
        }

        Bno2.setCellValueFactory(new PropertyValueFactory<>("Bno"));
        Bkind2.setCellValueFactory(new PropertyValueFactory<>("Bkind"));
        Bname2.setCellValueFactory(new PropertyValueFactory<>("Bname"));
        Bprice2.setCellValueFactory(new PropertyValueFactory<>("Bprice"));
        Bpub2.setCellValueFactory(new PropertyValueFactory<>("Bpub"));
        Bauther2.setCellValueFactory(new PropertyValueFactory<>("Bauther"));
        Boutdate.setCellValueFactory(new PropertyValueFactory<>("Bdateout"));
        Bindate.setCellValueFactory(new PropertyValueFactory<>("Bdatein"));
        userborrowtable.setItems(borrowdata);
        System.out.println("按作者查找");
        studentconnect.StudentConnClose();//关闭连接

    }

    @FXML
    public void userborrowtableclick() throws SQLException {   //为我的借阅tableview添加点击事件
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
            studentconnect.connect();  //建立连接
            Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
            c.add(Calendar.DATE, 20);
            int year = c.get(Calendar.YEAR);  //
            int month = c.get(Calendar.MONTH)+1;
            int day = c.get(Calendar.DATE);
            String indate = year+"-"+month+"-"+day;
            System.out.println(indate);
            int rs = studentconnect.Updateindate(borrowdata.get(index).getBno(),indate);
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
            studentconnect.StudentConnClose();//关闭连接
        } else if (result.get() == buttonTypeOne) { //归还
            studentconnect.connect();  //建立连接
            int flag = studentconnect.UpdateBookStatueTokejie(borrowdata.get(index).getBno()); //将图书表中该图书的状态修改为可借
            int flag1 = studentconnect.Deteleborrow(this.Snum,borrowdata.get(index).getBno()); //从借阅表中删除该条借阅记录
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
        studentconnect.StudentConnClose();
    }

    @FXML
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

    public static class userborrowbook {     //创建一个usersearchbook类，用来存放从数据库中查询到的图书的信息
        private final SimpleStringProperty Bno2;   //书号
        private final SimpleStringProperty Bkind2;  //书种类
        private final SimpleStringProperty Bname2;  //书名
        private final SimpleStringProperty Bauther2; //书作者
        private final SimpleStringProperty Bpub2;  //出版社
        private final SimpleStringProperty Bprice2;  //价格
        private final SimpleStringProperty Bdateout;  //借出日期
        private final SimpleStringProperty Bdatein;  //应还日期


        private userborrowbook(String bno2, String bkind2, String bname2,String bauther2,String bpub2,String bprice2,String bdateout,String bdatein) {//构造函数
            this.Bno2 = new SimpleStringProperty(bno2);
            this.Bkind2 = new SimpleStringProperty(bkind2);
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
