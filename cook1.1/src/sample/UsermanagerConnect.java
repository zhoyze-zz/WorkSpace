package sample;
/*
用于实现对数据库的操作的函数的类
*/
import javafx.scene.control.TextField;

import java.sql.*;

public class UsermanagerConnect {

    private String url=null;
    private String uname=null;
    private String pswd=null;
    private Connection conn;

    public UsermanagerConnect(String url1, String uname1, String pswd1) throws SQLException {  //通过一个构造函数传递参数
        url = url1;
        uname = uname1;
        pswd = pswd1;
    }

    public void StudentConnClose() throws SQLException { //用于关闭连接的函数
        conn.close();
    }

    public  void connect() throws SQLException {  //用于连接数据库的函数
        conn = DriverManager.getConnection(url, uname, pswd);
    }

    public ResultSet getUsermanagerInfo(String umnum) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from usermanager \n"+ " where UMnum = '"+umnum+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbysname(String name) throws SQLException {  //按照书名查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from student \n"+ " where Sname = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbybmname(String name) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from bookmanager \n"+ " where BMname = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbysunm(String name) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from student \n"+ " where Snum = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbybmnum(String name) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from bookmanager \n"+ " where BMnum = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbykind(String xueyuan,String grade) throws SQLException {  //按照种类查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from student \n"+ " where Sxueyuan = '"+xueyuan+"'and Sgrade = '"+grade+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbylib(String libname) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from bookmanager \n"+ " where Lname = '"+libname+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet studentshowall() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from student ");//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet bookmanageshowall() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from bookmanager ");//查询语句
        return rs; //将查询结果返回
    }

    public int DeteleStudent(String snum, String sbnum) throws SQLException {
        if (sbnum.equals("0")){
            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("delete \n" +
                    "from student\n" +
                    "where Snum = '"+snum+"'" );//更新语句
            return rs;
        }else{
            return 0;
        }
    }

    public int Detelebookmanager(String bmnum) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("delete \n" +
                "from bookmanager\n" +
                "where BMnum = '"+bmnum+"'" );//更新语句
        return rs;
    }

    public int Addstudent(String managesnum, String managesname, String managesgrade, String managespswd, String managesxueyuan, String managessex, String managesborrownum, String managesphone) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("insert \n" +
                "into student\n" +
                "values ('"+managesnum+"','"+managesname+"','"+managesgrade+"','"+managespswd+"','"+managesxueyuan+"','"+managesphone+"','"+managesborrownum+"','"+managessex+"')" );//查询语句
        return rs;
    }

    public int alterstudent(String beforeSnum,String Snum,String Sname,String Sgrade,String Spswd,String Sxueyuan,String Ssex,String Sborrownum,String Sphone) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("update student\n" +
                "set Snum='"+Snum+"',Sname='"+Sname+"',Sgrade='"+Sgrade+"',Spswd='"+Spswd+"',Sxueyuan='"+Sxueyuan+"',Sphone='"+Sphone+"',Sborrownum='"+Sborrownum+"',Ssex='"+Ssex+"'\n"+
                "where Snum='"+beforeSnum+"'");//查询语句
        return rs;
    }

    public int Addbokmanager(String managebmnum, String managebmname, String managebmpswd, String managebmphone, String managebmsex, String managebmsalary, String managelname) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("insert \n" +
                "into bookmanager\n" +
                "values ('"+managebmnum+"','"+managebmname+"','"+managebmpswd+"','"+managebmphone+"','"+managebmsex+"','"+managebmsalary+"','"+managelname+"')" );//查询语句
        return rs;
    }

    public int alterbookmanage(String beforebmnum, String managebmnum, String managebmname, String managebmpswd, String managebmphone, String managebmsex, String managebmsalary, String managelname) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("update bookmanager\n" +
                "set BMnum='"+managebmnum+"',BMname='"+managebmname+"',BMpswd='"+managebmpswd+"',BMphone='"+managebmphone+"',BMsex='"+managebmsex+"',BMsalary='"+managebmsalary+"',Lname='"+managelname+"'\n"+
                "where BMnum='"+beforebmnum+"'");//查询语句
        return rs;
    }
}
