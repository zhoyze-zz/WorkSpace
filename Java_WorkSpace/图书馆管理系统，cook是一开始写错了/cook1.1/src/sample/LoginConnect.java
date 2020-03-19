package sample;
/*
用于实现对数据库的操作的函数的类
*/
import java.sql.*;

public class LoginConnect {

    private String url=null;
    private String uname=null;
    private String pswd=null;
    private Connection conn;

    public LoginConnect(String url1, String uname1, String pswd1) throws SQLException {
        url = url1;
        uname = uname1;
        pswd = pswd1;
    }

    public  Boolean login(String username,String passwod ,String kind) throws SQLException {     //用于登录的函数，判断数据库中是否有该用户来返回一个值
        conn = DriverManager.getConnection(url, uname, pswd);
        //当查找到该用户时就返回true，允许登录。
        Statement statement = conn.createStatement();
        ResultSet rs = null;
        if (kind.equals("学生")){  //当选择用户类型为学生时
            rs = statement.executeQuery("select *\n" + "from student\n" + "where Snum = "+username+" and Spswd = "+passwod);
        }
        else if (kind.equals("图书管理员")){//当选择用户类型为图书管理员时
            rs = statement.executeQuery("select *\n" + "from bookmanager\n" + "where BMnum = "+username+" and BMpswd = "+passwod);
        }
        else if (kind.equals("用户管理员")){//当选择用户类型为用户管理员时
            rs = statement.executeQuery("select *\n" + "from usermanager\n" + "where UMnum = "+username+" and UMpswd = "+passwod);
        }
        boolean flag = rs.next();
        System.out.println("LoginConnect:"+flag);
        return flag;
    }

    public void close() throws SQLException {  //用于将连接关闭
        conn.close();
    }
}
