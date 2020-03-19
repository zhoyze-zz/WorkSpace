package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public Connect(String url,String uname,String pswd) throws SQLException {
        //url ="jdbc:sqlserver://localhost:1433;database=cook" ;
        //java和数据库的连接
        Connection conn = DriverManager.getConnection(url, uname, pswd);
    }
}
